package com.jeanboy.data.repository.handler;

/**
 * Created by jeanboy on 2017/9/30.
 */

public interface RepositoryCallback<ResultType> {

    void onSucceed(ResultType result);

    void onError(String message);
}
