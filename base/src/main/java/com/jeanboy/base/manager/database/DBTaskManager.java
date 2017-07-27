package com.jeanboy.base.manager.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jeanboy on 2017/2/27.
 */

class DBTaskManager {

    private static ExecutorService cachedExecutor = Executors.newCachedThreadPool();
    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

    private static class SingletonHolder {
        private static DBTaskManager instance = new DBTaskManager();
    }

    public static DBTaskManager getInstance() {
        return SingletonHolder.instance;
    }

    private DBTaskManager() {
    }

    void getData(Runnable runnable) {
        cachedExecutor.execute(runnable);
    }

    void putData(Runnable runnable) {
        singleExecutor.execute(runnable);
    }
}
