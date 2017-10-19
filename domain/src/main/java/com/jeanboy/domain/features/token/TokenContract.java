package com.jeanboy.domain.features.token;

import com.jeanboy.data.cache.database.model.TokenModel;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class TokenContract {

    public interface View {

        void onGetTokenSucceed(TokenModel tokenModel);

        void onGetTokenError();
    }

    public interface Presenter {

        void getToken(String username, String password);
    }
}
