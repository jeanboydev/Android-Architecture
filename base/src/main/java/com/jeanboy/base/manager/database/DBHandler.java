package com.jeanboy.base.manager.database;

import android.content.Context;

import java.util.List;

/**
 * Created by jeanboy on 2016/12/14.
 */

public interface DBHandler {

    void init(Context context);

    <T> long save(T t);

    <T> long saveOrUpdate(T t);

    <T> void delete(T t);

    <T> void clear(Class<T> clazz);

    <T> T getById(Class<T> clazz, Long id);

    <T> List<T> getAll(Class<T> clazz);

    void clearCache();

    /**
     * @param clazz
     * @param where  ex: where begin_date_time >= ? AND end_date_time <= ?
     * @param params
     * @param <T>
     * @return
     */
    <T> List<T> query(Class<T> clazz, String where, String... params);
}
