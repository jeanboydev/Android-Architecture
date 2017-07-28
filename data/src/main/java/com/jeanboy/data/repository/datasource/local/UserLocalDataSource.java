package com.jeanboy.data.repository.datasource.local;

import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.datasource.UserDataSource;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserLocalDataSource implements UserDataSource.Local {

    private static UserLocalDataSource INSTANCE;

    public static UserLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void save(UserModel userModel, SourceCallback<Long> callback) {
        // TODO: 2017/7/28 保存到本地
    }

    @Override
    public void saveOrUpdate(UserModel userModel, SourceCallback<String> callback) {
        // TODO: 2017/7/28 保存或更新本地数据
    }

    @Override
    public void get(Long id, SourceCallback<UserModel> callback) {
        // TODO: 2017/7/28 根据id获取
    }

    @Override
    public void getAll(SourceCallback<List<UserModel>> callback) {
        // TODO: 2017/7/28 获取全部
    }

    @Override
    public void delete(UserModel userModel) {
        // TODO: 2017/7/28 根据id删除
    }

    @Override
    public void clear() {
        // TODO: 2017/7/28 清空
    }

    @Override
    public void getByUsername(String username, SourceCallback<UserModel> callback) {
        // TODO: 2017/7/28 根据用户名获取
    }
}
