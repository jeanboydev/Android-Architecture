package com.jeanboy.domain.features.token;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.domain.base.BasePresenter;

/**
 * Created by jeanboy on 2017/7/28.
 */

public class TokenPresenter extends BasePresenter<TokenContract.View> implements TokenContract.Presenter {

    public TokenPresenter(TokenContract.View view) {
        super(view);
    }

    private TokenViewModel loginViewModel;

    @Override
    public void onViewCreated(LifecycleOwner owner) {
        loginViewModel = getViewModel(owner, TokenViewModel.class);
        if (loginViewModel == null) return;
        loginViewModel.getTokenData().observe(owner, new Observer<TokenModel>() {
            @Override
            public void onChanged(@Nullable TokenModel tokenModel) {
                if (tokenModel == null) {
                    view.onGetTokenError();
                    return;
                }
                view.onGetTokenSucceed(tokenModel);
            }
        });
    }

    @Override
    public void getToken(String username, String password) {
        loginViewModel.login(username, password);
    }

}
