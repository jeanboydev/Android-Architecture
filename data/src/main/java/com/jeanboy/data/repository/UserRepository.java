package com.jeanboy.data.repository;

import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.ResponseData;
import com.jeanboy.data.base.BaseRepository;
import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.net.entity.TokenEntity;
import com.jeanboy.data.net.entity.UserEntity;
import com.jeanboy.data.repository.datasource.UserDataSource;
import com.jeanboy.data.repository.datasource.local.UserLocalDataSource;
import com.jeanboy.data.repository.datasource.remote.UserRemoteDataSource;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

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
        localDataSource.save(userModel, callback);
    }

    @Override
    public void saveOrUpdate(UserModel userModel, SourceCallback<String> callback) {
        localDataSource.saveOrUpdate(userModel, callback);
    }

    @Override
    public void get(Long id, SourceCallback<UserModel> callback) {
        localDataSource.get(id, callback);
    }

    @Override
    public void getAll(SourceCallback<List<UserModel>> callback) {
        localDataSource.getAll(callback);
    }

    @Override
    public void delete(UserModel userModel) {
        localDataSource.delete(userModel);
    }

    @Override
    public void clear() {
        localDataSource.clear();
    }

    @Override
    public void getByUsername(String username, SourceCallback<UserModel> callback) {
        localDataSource.getByUsername(username, callback);
    }

    @Override
    public Call<TokenEntity> login(String username, String password, RequestCallback<ResponseData<TokenEntity>> callback) {
        return remoteDataSource.login(username, password, callback);
    }

    @Override
    public Call<UserEntity> getInfo(String accessToken, String userId, RequestCallback<ResponseData<UserEntity>> callback) {
        return remoteDataSource.getInfo(accessToken, userId, callback);
    }

    @Override
    public Call<List<UserEntity>> getFriendList(String accessToken, String userId, int skip, int limit, RequestCallback<ResponseData<List
            <UserEntity>>> callback) {
        return remoteDataSource.getFriendList(accessToken, userId, skip, limit, callback);
    }
}
