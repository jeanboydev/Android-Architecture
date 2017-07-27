package com.jeanboy.data.repository.datasource.local;

import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.datasource.UserDataSource;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserLocalDataSource implements UserDataSource.Local {

    @Override
    public void save(UserModel userModel, SourceCallback<Long> callback) {

    }

    @Override
    public void saveOrUpdate(UserModel userModel, SourceCallback<String> callback) {

    }

    @Override
    public void get(Long id, SourceCallback<UserModel> callback) {

    }

    @Override
    public void getAll(SourceCallback<List<UserModel>> callback) {

    }

    @Override
    public void delete(UserModel userModel) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void getByUsername(String username, SourceCallback<UserModel> callback) {

    }
}
