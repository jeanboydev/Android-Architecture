package com.jeanboy.data.cache.database.model;

/**
 * Created by jeanboy on 2017/7/28.
 */

public class TokenModel {

    private String tokenType;//令牌类型
    private String refreshToken;//更新令牌，用来获取下一次的访问令牌
    private String accessToken;//访问令牌
    private int expiresIn;//过期时间
    private long createTimeAt;//创建时间

    public TokenModel() {
        createTimeAt = System.currentTimeMillis();
    }

    public boolean isInvalid() {
        return System.currentTimeMillis() - createTimeAt > expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

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

    public long getCreateTimeAt() {
        return createTimeAt;
    }

    public void setCreateTimeAt(long createTimeAt) {
        this.createTimeAt = createTimeAt;
    }
}
