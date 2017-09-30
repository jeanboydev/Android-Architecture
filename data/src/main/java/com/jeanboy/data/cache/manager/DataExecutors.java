package com.jeanboy.data.cache.manager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jeanboy on 2017/9/29.
 */

public class DataExecutors {

    private final Executor singleExecutor;
    private final Executor multipleExecutor;

    private static class SingletonHolder {
        private static DataExecutors instance = new DataExecutors();
    }

    public static DataExecutors getInstance() {
        return SingletonHolder.instance;
    }

    private DataExecutors(Executor singleExecutor, Executor multipleExecutor) {
        this.singleExecutor = singleExecutor;
        this.multipleExecutor = multipleExecutor;
    }

    private DataExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newCachedThreadPool());
    }

    public void load(Runnable runnable) {
        multipleExecutor.execute(runnable);
    }

    public void put(Runnable runnable) {
        singleExecutor.execute(runnable);
    }
}
