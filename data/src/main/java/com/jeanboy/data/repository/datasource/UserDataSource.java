package com.jeanboy.data.repository.datasource;

import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.ResponseData;
import com.jeanboy.data.base.BaseLocalDataSource;
import com.jeanboy.data.base.SourceCallback;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.net.entity.TokenEntity;
import com.jeanboy.data.net.entity.UserEntity;

import java.util.List;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserDataSource {

    public interface Local extends BaseLocalDataSource<UserModel> {
        // TODO: 2017/7/27 本地缓存自定义
        void getByUsername(String username, SourceCallback<UserModel> callback);
    }

    public interface Remote {
        // TODO: 2017/7/27 API 接口定义
        Call<TokenEntity> login(String username, String password, RequestCallback<ResponseData<TokenEntity>> callback);

        Call<UserEntity> getInfo(String accessToken, String userId, RequestCallback<ResponseData<UserEntity>> callback);

        Call<List<UserEntity>> getFriendList(String accessToken, String userId, int skip, int limit,
                                             RequestCallback<ResponseData<List<UserEntity>>> callback);
    }
}
