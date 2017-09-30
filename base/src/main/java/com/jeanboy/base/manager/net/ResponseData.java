package com.jeanboy.base.manager.net;

import java.util.Map;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class ResponseData<B> {

    private int code;
    private Map<String, Object> headers;
    private B body;
    private String message;

    public ResponseData() {
    }

    public ResponseData(B body) {
        this.code = StatusCode.CODE_OK;
        this.body = body;
    }

    public ResponseData(String message) {
        this.code = StatusCode.CODE_BAD_REQUEST;
        this.message = message;
    }

    public ResponseData(int code, Map<String, Object> headers, B body) {
        this.code = code;
        this.headers = headers;
        this.body = body;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public B getBody() {
        return body;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
