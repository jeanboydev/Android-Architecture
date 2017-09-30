package com.jeanboy.data.repository.handler;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jeanboy.data.cache.manager.DataExecutors;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeanboy on 2017/9/30.
 */

public abstract class RepositoryHandler<ResponseType, ResultType> {

    private MediatorLiveData<ResultType> resultData = new MediatorLiveData<>();
    private MutableLiveData<ResultType> memoryData = new MutableLiveData<>();

    public RepositoryHandler() {

        loadCache();//先从缓存中获取
        resultData.addSource(memoryData, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType result) {//缓存数据变化
                if (shouldFetch(result)) {//是否需要加载新数据
                    loadRemote();
                } else {
                    resultData.setValue(result);
                }
            }
        });
    }

    private void loadRemote() {
        fetchFromNetWork().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseType>() {
                    @Override
                    public void accept(@NonNull final ResponseType responseType) throws Exception {
                        if (responseType == null) return;
                        ResultType resultType = onMapper(responseType);
                        doCache(resultType);
                        onFetchSucceed(resultType);
                        memoryData.setValue(resultType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        onFetchFailed(throwable.getMessage());
                    }
                });
    }

    private void loadCache() {
        DataExecutors.getInstance().load(new Runnable() {
            @Override
            public void run() {
                LiveData<ResultType> fromCache = loadFromCache();
                if (fromCache != null) {
                    memoryData.setValue(fromCache.getValue());
                }
            }
        });
    }

    private void doCache(final ResultType result) {
        DataExecutors.getInstance().put(new Runnable() {
            @Override
            public void run() {
                saveToCache(result);
            }
        });
    }

    public LiveData<ResultType> asLiveData() {
        return resultData;
    }

    protected LiveData<ResultType> loadFromCache() {
        return null;
    }

    protected void saveToCache(ResultType result) {
    }

    protected abstract boolean shouldFetch(ResultType result);

    protected abstract Flowable<ResponseType> fetchFromNetWork();

    protected abstract ResultType onMapper(ResponseType responseType);

    protected void onFetchFailed(String message) {
    }

    protected void onFetchSucceed(ResultType result) {
    }
}
