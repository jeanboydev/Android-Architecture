package com.jeanboy.data.net.manager;

import android.annotation.SuppressLint;

import com.jeanboy.base.BuildConfig;
import com.jeanboy.base.manager.net.NetManager;
import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.RequestParams;
import com.jeanboy.base.manager.net.ResponseData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class OkHttpManager extends NetManager {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private final static int CONNECT_TIMEOUT = 30;

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static OkHttpManager instance = new OkHttpManager();
    }

    public static OkHttpManager getInstance() {
        return OkHttpManager.SingletonHolder.instance;
    }

    private OkHttpManager() {
        super();
    }

    public <T> void doBack(RequestParams<Call<T>> request, RequestCallback<ResponseData<T>> callback) {
        doBack(new OkHttpHandler<T>(), request, callback);
    }

    public <T> void doSync(RequestParams<Call<T>> request, RequestCallback<ResponseData<T>> callback) {
        doSync(new OkHttpHandler<T>(), request, callback);
    }

    public <T> T create(String baseUrl, Class<T> clazz) {
        return getRetrofitByGson(baseUrl).create(clazz);
    }

    public <T> T createByXml(String baseUrl, Class<T> clazz) {
        return getRetrofitByXml(baseUrl).create(clazz);
    }

    /*------------------------配置环境------------------------------*/

    private Retrofit getRetrofitByGson(String baseUrl) {
        return getRetrofit(baseUrl, GsonConverterFactory.create());
    }

    private Retrofit getRetrofitByXml(String baseUrl) {
        return getRetrofit(baseUrl, SimpleXmlConverterFactory.create());
    }

    private Retrofit getRetrofit(String baseUrl, Converter.Factory factory) {
        if (mOkHttpClient == null) {
            mOkHttpClient = getOkHttpClient();
        }
        mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(factory).client(mOkHttpClient).build();
        return mRetrofit;
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).retryOnConnectionFailure(true)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).build();
        }
        return mOkHttpClient;
    }
}
