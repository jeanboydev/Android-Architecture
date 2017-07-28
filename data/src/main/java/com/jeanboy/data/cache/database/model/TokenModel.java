package com.jeanboy.data.cache.database.model;

/**
 * Created by jeanboy on 2017/7/28.
 */

public class TokenModel {

    private String refreshToken;//获取accessToken
    private String accessToken;//授权令牌
    private int expiresIn;//过期时长

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
