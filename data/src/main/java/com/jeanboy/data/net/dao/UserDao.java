package com.jeanboy.data.net.dao;

import com.jeanboy.data.net.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jeanboy on 2017/7/27.
 */

public interface UserDao {

    String BASE_URL = "http://www.xxx.com";

    /**
     * http://www.xxx.com?username=xxx&password=xxx
     *
     * @param username
     * @param password
     * @return
     */
    @POST("/user")
    Call<UserEntity> login(@Query("username") String username, @Query("password") String password);
}
