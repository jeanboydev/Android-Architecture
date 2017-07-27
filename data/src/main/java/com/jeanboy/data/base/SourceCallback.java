package com.jeanboy.data.base;

/**
 * Created by jeanboy on 2017/3/9.
 */

public interface SourceCallback<T> {

    void onLoaded(T t);

    void onDataNotAvailable();
}
