package com.jeanboy.data.repository;

import com.jeanboy.data.base.BaseRepository;
import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.datasource.UserDataSource;
import com.jeanboy.data.repository.datasource.local.UserLocalDataSource;
import com.jeanboy.data.repository.datasource.remote.UserRemoteDataSource;

import java.util.List;
import java.util.Map;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserRepository extends BaseRepository implements UserDataSource.Local, UserDataSource.Remote {

    private static UserRepository INSTANCE = null;

    Map<String, UserModel> mCacheMap;//memory cache

    private final UserLocalDataSource localDataSource;
    private final UserRemoteDataSource remoteDataSource;

    public UserRepository(UserLocalDataSource localDataSource, UserRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static UserRepository getInstance(UserLocalDataSource localDataSource, UserRemoteDataSource
            remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

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

    @Override
    public void login(String username, String password) {

    }
}
