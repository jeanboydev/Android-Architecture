package com.jeanboy.data.mapper;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.net.entity.TokenEntity;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class TokenDataMapper {

    public TokenDataMapper() {
    }

    public TokenModel transform(TokenEntity tokenEntity) {
        TokenModel tokenModel=new TokenModel();
        tokenModel.setRefreshToken(tokenEntity.getRefreshToken());
        tokenModel.setAccessToken(tokenEntity.getAccessToken());
        tokenModel.setExpiresIn(tokenEntity.getExpiresIn());
        return tokenModel;
    }
}
