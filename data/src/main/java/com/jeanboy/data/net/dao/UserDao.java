package com.jeanboy.data.net.dao;

import com.jeanboy.data.net.entity.TokenEntity;
import com.jeanboy.data.net.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jeanboy on 2017/7/27.
 */

public interface UserDao {

    String BASE_URL = "http://www.xxx.com";

    /**
     * http://www.xxx.com
     * POST {
     * username:xxx,
     * password:xxx
     * }
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/user")
    Flowable<TokenEntity> login(@Field("username") String username, @Field("password") String password);

    /**
     * http://www.xxx.com/user/id
     * GET
     * Header {
     * Authorization : accessToken
     * }
     *
     * @param accessToken
     * @param userId
     * @return
     */
    @GET("/user/{id}")
    Flowable<UserEntity> getInfo(@Header("Authorization") String accessToken, @Path("id") String userId);

    /**
     * http://www.xxx.com/user/id/friend?skip=xxx&limit=xxx
     * GET
     * Header {
     * Authorization : accessToken
     * }
     *
     * @param accessToken
     * @param userId
     * @param skip
     * @param limit
     * @return
     */
    @GET("/user/{id}/friend")
    Flowable<List<UserEntity>> getFriendList(@Header("Authorization") String accessToken, @Path("id") String userId, @Query("skip") int skip,
                                         @Query("limit") int limit);
}
