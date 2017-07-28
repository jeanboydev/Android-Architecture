package com.jeanboy.base.manager.net;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class RequestParams<P> {

    private P params;

    public RequestParams(P body) {
        this.params = body;
    }

    public P getParams() {
        return params;
    }
}
