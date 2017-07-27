package com.jeanboy.base.manager.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

/**
 * Created by jeanboy on 2016/12/9.
 */

public class DBManager {

    private Context context;

    private DBHandler dbHandler;

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static DBManager instance = new DBManager();
    }

    private DBManager() {
    }

    public static DBManager getInstance() {
        return SingletonHolder.instance;
    }

    public void build(Context context, DBHandler dbHandler) {
        this.context = context.getApplicationContext();
        this.dbHandler = (dbHandler);
        this.dbHandler.init(this.context);
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    public interface Callback<T> {
        void onFinish(T t);
    }


    public <T> void save(final T t, final Callback<Long> callback) {
        DBTaskManager.getInstance().putData(new Runnable() {
            @Override
            public void run() {
                final long id = dbHandler.save(t);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinish(id);
                        }
                    }
                });
            }
        });
    }

    public <T> void saveOrUpdate(final T t, final Callback<String> callback) {
        DBTaskManager.getInstance().putData(new Runnable() {
            @Override
            public void run() {
                dbHandler.saveOrUpdate(t);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinish("saveOrUpdate finish!");
                        }
                    }
                });
            }
        });
    }

    public <T> void delete(final T t, final Callback<String> callback) {
        DBTaskManager.getInstance().putData(new Runnable() {
            @Override
            public void run() {
                dbHandler.delete(t);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinish("delete finish!");
                        }
                    }
                });
            }
        });
    }

    public <T> void clear(final Class<T> clazz, final Callback<String> callback) {
        DBTaskManager.getInstance().putData(new Runnable() {
            @Override
            public void run() {
                dbHandler.clear(clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinish("delete all finish!");
                        }
                    }
                });
            }
        });
    }

    public <T> void getById(final Class<T> clazz, final Long id, final Callback<T> callback) {
        DBTaskManager.getInstance().getData(new Runnable() {
            @Override
            public void run() {
                final T t = dbHandler.getById(clazz, id);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFinish(t);
                        }
                    }
                });
            }
        });
    }

    public <T> void getAll(final Class<T> clazz, final Callback<List<T>> callback) {
        DBTaskManager.getInstance().getData(new Runnable() {
            @Override
            public void run() {
                final List<T> ts = dbHandler.getAll(clazz);
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

    public void clearCache() {
        dbHandler.clearCache();
    }


    /**
     * @param clazz
     * @param where  ex: where begin_date_time >= ? AND end_date_time <= ?
     * @param params
     * @param <T>
     */
    public <T> void query(final Class<T> clazz, final String where, final String[] params, final Callback<List<T>> callback) {
        DBTaskManager.getInstance().getData(new Runnable() {
            @Override
            public void run() {
                final List<T> ts = dbHandler.query(clazz, where, params);
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

    /*--green DAO--*/
//    public <T> QueryBuilder<T> queryBuilder(Class<T> clazz) {
//        return this.dbHandler.queryBuilder(clazz);
//    }
//
//    public <T> void query(final QueryBuilder<T> queryBuilder, final Callback<List<T>> callback) {
//        DBTaskManager.getInstance().getData(new Runnable() {
//            @Override
//            public void run() {
//                final List<T> ts = queryBuilder.list();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (callback != null) {
//                            callback.onFinish(ts);
//                        }
//                    }
//                });
//            }
//        });
//    }
}
