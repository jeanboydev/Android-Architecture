package com.jeanboy.data.repository.datasource.local;

import com.jeanboy.base.manager.database.DBManager;
import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.dao.UserModelDao;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.cache.manager.GreenDaoManager;
import com.jeanboy.data.repository.datasource.UserDataSource;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserLocalDataSource implements UserDataSource.Local {

    private static UserLocalDataSource INSTANCE;

    @Inject
    public UserLocalDataSource() {
    }

    public static UserLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void save(UserModel userModel, final SourceCallback<Long> callback) {
        // TODO: 2017/7/28 保存到本地
        DBManager.getInstance().save(userModel, new DBManager.Callback<Long>() {
            @Override
            public void onFinish(Long aLong) {
                if (callback == null) return;
                if (aLong == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onLoaded(aLong);
            }
        });
    }

    @Override
    public void saveOrUpdate(UserModel userModel, final SourceCallback<String> callback) {
        // TODO: 2017/7/28 保存或更新本地数据
        DBManager.getInstance().saveOrUpdate(userModel, new DBManager.Callback<String>() {
            @Override
            public void onFinish(String s) {
                if (callback == null) return;
                callback.onLoaded(s);
            }
        });
    }

    @Override
    public void get(Long id, final SourceCallback<UserModel> callback) {
        // TODO: 2017/7/28 根据id获取
        DBManager.getInstance().getById(UserModel.class, id, new DBManager.Callback<UserModel>() {
            @Override
            public void onFinish(UserModel dbModel) {
                if (callback == null) return;
                if (dbModel == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onLoaded(dbModel);
            }
        });
    }

    @Override
    public void getAll(final SourceCallback<List<UserModel>> callback) {
        // TODO: 2017/7/28 获取全部
        DBManager.getInstance().getAll(UserModel.class, new DBManager.Callback<List<UserModel>>() {
            @Override
            public void onFinish(List<UserModel> dbModels) {
                if (callback == null) return;
                if (dbModels == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                if (dbModels.size() == 0) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onLoaded(dbModels);
            }
        });
    }

    @Override
    public void delete(UserModel userModel) {
        // TODO: 2017/7/28 根据id删除
        DBManager.getInstance().delete(userModel, null);
    }

    @Override
    public void clear() {
        // TODO: 2017/7/28 清空
        DBManager.getInstance().clear(UserModel.class, null);
    }

    @Override
    public void getByUsername(String username, final SourceCallback<UserModel> callback) {
        // TODO: 2017/7/28 根据用户名获取
        QueryBuilder<UserModel> queryBuilder = GreenDaoManager.getInstance().queryBuilder(UserModel.class);
        queryBuilder.where(UserModelDao.Properties.UserName.eq(username));
        GreenDaoManager.getInstance().query(queryBuilder, new DBManager.Callback<List<UserModel>>() {
            @Override
            public void onFinish(List<UserModel> dbModels) {
                if (callback == null) return;
                if (dbModels == null) {
                    callback.onDataNotAvailable();
                    return;
                }
                if (dbModels.size() > 0) {
                    callback.onLoaded(dbModels.get(0));
                }
            }
        });
    }
}
