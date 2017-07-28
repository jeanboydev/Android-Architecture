package com.jeanboy.domain.features.login;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.domain.base.BasePresenter;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class LoginContract {

    public interface View {

        void loginSucceed(TokenModel tokenModel);

        void loginError();
    }

    public interface Presenter extends BasePresenter<View> {

        void login(String username, String password);
    }
}
