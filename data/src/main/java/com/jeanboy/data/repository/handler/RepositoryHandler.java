package com.jeanboy.data.repository.handler;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.RequestParams;
import com.jeanboy.base.manager.net.ResponseData;
import com.jeanboy.data.cache.manager.DataExecutors;
import com.jeanboy.data.net.manager.OkHttpManager;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/9/30.
 */

public abstract class RepositoryHandler<ResponseType, ResultType> {

    private MutableLiveData<ResultType> memoryData = new MutableLiveData<>();
    private MediatorLiveData<ResultType> resultData = new MediatorLiveData<>();

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
        Call<ResponseType> fromNetWork = fetchFromNetWork();
        if (fromNetWork == null) {
            memoryData.setValue(null);
            return;
        }

        OkHttpManager.getInstance().doBack(new RequestParams<>(fromNetWork),
                new RequestCallback<ResponseData<ResponseType>>() {
            @Override
            public void onSuccess(ResponseData<ResponseType> response) {
                if (response == null) return;
                ResultType resultType = onMapper(response.getBody());
                doCache(resultType);
                memoryData.setValue(resultType);
            }

            @Override
            public void onError(int code, String msg) {
                memoryData.setValue(null);
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
                } else {
                    memoryData.setValue(null);
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

    protected abstract Call<ResponseType> fetchFromNetWork();

    protected abstract ResultType onMapper(ResponseType responseType);
}
