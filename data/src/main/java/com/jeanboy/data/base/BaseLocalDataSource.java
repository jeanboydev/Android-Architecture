package com.jeanboy.data.base;


import java.util.List;

/**
 * Created by jeanboy on 2017/3/13.
 */

public interface BaseLocalDataSource<T> {

    void save(T t, SourceCallback<Long> callback);

    void saveOrUpdate(T t, SourceCallback<String> callback);

    void get(Long id, SourceCallback<T> callback);

    void getAll(SourceCallback<List<T>> callback);

    void delete(T t);

    void clear();
}
