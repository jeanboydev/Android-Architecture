package com.jeanboy.data.cache.manager;

import android.annotation.SuppressLint;

import com.jeanboy.base.manager.database.DBManager;
import com.jeanboy.base.manager.database.DBTaskManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/31.
 */

public class GreenDaoManager extends DBManager {


    private GreenDaoManager() {
        super();
    }

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static GreenDaoManager instance = new GreenDaoManager();
    }

    public static GreenDaoManager getInstance() {
        return SingletonHolder.instance;
    }

    public <T> QueryBuilder<T> queryBuilder(Class<T> clazz) {
        if (this.dbHandler instanceof GreenDaoHandler) {
            return ((GreenDaoHandler) dbHandler).queryBuilder(clazz);
        }
        return null;
    }

    public <T> void query(final QueryBuilder<T> queryBuilder, final Callback<List<T>> callback) {
        DBTaskManager.getInstance().getData(new Runnable() {
            @Override
            public void run() {
                final List<T> ts = queryBuilder.list();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinish(ts);
                        }
                    }
                });
            }
        });
    }
}
