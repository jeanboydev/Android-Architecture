package com.jeanboy.data.net.mapper;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.net.entity.UserEntity;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserDataMapper {

    public UserDataMapper() {
    }

    public UserModel transform(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUserName(userEntity.getUsername());
        userModel.setUserNick(userEntity.getUserNick());
        userModel.setCreateTime(System.currentTimeMillis());
        return userModel;
    }
}
