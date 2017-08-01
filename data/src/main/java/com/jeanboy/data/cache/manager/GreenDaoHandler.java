package com.jeanboy.data.cache.manager;

import android.content.Context;

import com.jeanboy.base.manager.database.DBHandler;
import com.jeanboy.data.cache.database.dao.DaoMaster;
import com.jeanboy.data.cache.database.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/31.
 */

public class GreenDaoHandler implements DBHandler {

    private final static String DB_NAME = "data.db";
    private Context context;
    private DaoSession daoSession;

    private DaoMaster getDaoMaster(Context context) {
        //DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, AppConfig.DB_NAME, null);
        MyDevOpenHelper devOpenHelper = new MyDevOpenHelper(context, DB_NAME, null);
        return new DaoMaster(devOpenHelper.getWritableDatabase());
    }

    private DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            daoSession = getDaoMaster(context).newSession();
        }
        return daoSession;
    }

    @Override
    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public <T> long save(T t) {
        return getDaoSession(context).insert(t);
    }

    @Override
    public <T> long saveOrUpdate(T t) {
        return getDaoSession(context).insertOrReplace(t);
    }

    @Override
    public <T> void delete(T t) {
        getDaoSession(context).delete(t);
    }

    @Override
    public <T> void clear(Class<T> clazz) {
        getDaoSession(context).deleteAll(clazz);
    }

    @Override
    public <T> T getById(Class<T> clazz, Long id) {
        return getDaoSession(context).load(clazz, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        return getDaoSession(context).loadAll(clazz);
    }

    @Override
    public  void clearCache() {
        getDaoSession(context).clear();
    }

    @Override
    public <T> List<T> query(Class<T> clazz, String where, String[] params) {
        return getDaoSession(context).queryRaw(clazz, where, params);
    }

    /**
     * Query query = userDao.queryBuilder().where(
     * Properties.FirstName.eq("Joe"), Properties.YearOfBirth.eq(1970))
     * .build();
     * List joesOf1970 = query.list();
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> QueryBuilder<T> queryBuilder(Class<T> clazz) {
        return getDaoSession(context).queryBuilder(clazz);
    }
}
