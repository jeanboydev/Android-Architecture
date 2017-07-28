package com.jeanboy.data.manager;

import android.text.TextUtils;

import com.jeanboy.base.manager.net.NetHandler;
import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.RequestParams;
import com.jeanboy.base.manager.net.ResponseData;
import com.jeanboy.base.manager.net.StatusCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class OkHttpHandler<T> implements NetHandler<Call<T>, T> {

    @Override
    public void doBack(RequestParams<Call<T>> requestValues, final RequestCallback<ResponseData<T>> callback) {
        requestValues.getParams().enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    Headers headers = response.headers();
                    Map<String, Object> headerMap = new HashMap<>();
                    for (String name : headers.names()) {
                        headerMap.put(name, headers.get(name));
                    }
                    ResponseData<T> responseData = new ResponseData<>(response.code(), headerMap, response.body());
                    if (callback != null) {
                        callback.onSuccess(responseData);
                    }
                } else {
                    String msg = null;
                    try {
                        msg = response.errorBody().string();
                    } catch (IOException e) {
                        if (callback != null) {
                            callback.onError(response.code(), e.getMessage());
                        }
                    }
                    if (TextUtils.isEmpty(msg)) {
                        msg = response.message();
                    }
                    if (callback != null) {
                        callback.onError(response.code(), msg);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (callback != null) {
                    callback.onError(StatusCode.CODE_UNKNOWN_EXCEPTION, t.getMessage());
                }
            }
        });
    }

    @Override
    public void doSync(RequestParams<Call<T>> requestValues, RequestCallback<ResponseData<T>> callback) {
        try {
            Response<T> response = requestValues.getParams().execute();
            if (response.isSuccessful()) {
                Headers headers = response.headers();
                Map<String, Object> headerMap = new HashMap<>();
                for (String name : headers.names()) {
                    headerMap.put(name, headers.get(name));
                }
                ResponseData<T> responseBody = new ResponseData<>(response.code(), headerMap, response.body());
                callback.onSuccess(responseBody);
            } else {
                String msg = null;
                try {
                    msg = response.errorBody().string();
                } catch (IOException e) {
                    callback.onError(response.code(), e.getMessage());
                }
                if (TextUtils.isEmpty(msg)) {
                    msg = response.message();
                }
                if (callback != null) {
                    callback.onError(response.code(), msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (callback != null) {
                callback.onError(StatusCode.CODE_UNKNOWN_EXCEPTION, e.getMessage());
            }
        }
    }
}
