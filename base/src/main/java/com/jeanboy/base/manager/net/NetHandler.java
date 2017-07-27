package com.jeanboy.base.manager.net;

/**
 * Created by jeanboy on 2017/2/10.
 */

public interface NetHandler<P, T> {

    void doBack(RequestParams<P> requestValues, RequestCallback<ResponseData<T>> callback);

    void doSync(RequestParams<P> requestValues, RequestCallback<ResponseData<T>> callback);

}
