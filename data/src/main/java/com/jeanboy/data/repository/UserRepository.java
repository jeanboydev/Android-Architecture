package com.jeanboy.data.repository;

import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.ResponseData;
import com.jeanboy.data.base.BaseRepository;
import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.net.entity.TokenEntity;
import com.jeanboy.data.net.entity.UserEntity;
import com.jeanboy.data.net.mapper.UserDataMapper;
import com.jeanboy.data.repository.datasource.UserDataSource;
import com.jeanboy.data.repository.datasource.local.UserLocalDataSource;
import com.jeanboy.data.repository.datasource.remote.UserRemoteDataSource;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserRepository extends BaseRepository implements UserDataSource.Local, UserDataSource.Remote {

    private static UserRepository INSTANCE = null;

    Map<String, UserModel> mCacheMap;//memory cache

    private final UserLocalDataSource localDataSource;
    private final UserRemoteDataSource remoteDataSource;

    @Inject
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

    /**
     * 数据缓存逻辑
     *
     * @param userId
     * @param callback
     */
    public void getUserInfo(final String userId, final SourceCallback<UserModel> callback) {
        UserModel userModel = getFromMemory(userId);

        //内存缓存没有失效，优先从内存取出
        if (userModel != null && !mCacheIsDirty) {
            callback.onLoaded(userModel);
            return;
        }

        if (mCacheIsDirty) {//缓存数据需要更新
            getFromRemote(userId, callback);
        } else {//读取缓存数据
            get(Long.parseLong(userId), new SourceCallback<UserModel>() {
                @Override
                public void onLoaded(UserModel userModel) {
                    // TODO: 2017/8/3 刷新内存中缓存
                    refreshMemoryCache(userModel);
                    callback.onLoaded(userModel);
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
        }
    }

    private void getFromRemote(String userId, final SourceCallback<UserModel> callback) {
        getInfo("", userId, new RequestCallback<ResponseData<UserEntity>>() {
            @Override
            public void onSuccess(ResponseData<UserEntity> response) {
                UserEntity body = response.getBody();
                if (body == null) return;
                // TODO: 2017/7/28 mapper数据转换层
                UserModel userModel = new UserDataMapper().transform(body);
                // TODO: 2017/8/3 缓存数据
                refreshLocalData(userModel);
                callback.onLoaded(userModel);
            }

            @Override
            public void onError(int code, String msg) {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshLocalData(final UserModel userModel) {
        save(userModel, new SourceCallback<Long>() {
            @Override
            public void onLoaded(Long aLong) {
                userModel.setId(aLong);
                // TODO: 2017/8/3 刷新内存中缓存
                refreshMemoryCache(userModel);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private UserModel getFromMemory(String userId) {
        return mCacheMap.get(String.valueOf(userId));
    }

    private void refreshMemoryCache(UserModel userModel) {
        mCacheMap.put(String.valueOf(userModel.getId()), userModel);
    }
}
