package com.jeanboy.domain.features.usernew;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.cache.database.model.UserModel;


/**
 * Created by jeanboy on 2017/9/30.
 */

public class UserPresenter extends BasePresenterNew<UserContract.View> implements UserContract.PresenterNew {


    public UserPresenter(UserContract.View view) {
        super(view);
    }

    private UserViewModel userViewModel;
    private String targetUserId = null;

    @Override
    public void onViewCreated(LifecycleOwner owner) {
        userViewModel = getViewModel(owner, UserViewModel.class);
        if (userViewModel == null) return;
        userViewModel.getTokenData().observe(owner, new Observer<TokenModel>() {
            @Override
            public void onChanged(@Nullable TokenModel tokenModel) {
                if (tokenModel == null) {
                    view.onLoginError();
                    return;
                }
                userViewModel.getInfo(targetUserId);
            }
        });

        userViewModel.getUserData().observe(owner, new Observer<UserModel>() {
            @Override
            public void onChanged(@Nullable UserModel userModel) {
                if (userModel == null) {
                    view.onInfoError();
                    return;
                }
                view.onInfoChange(userModel);
            }
        });
    }

    @Override
    public void login(String username, String password) {
        userViewModel.login(username, password);
    }

    @Override
    public void getInfo(String userId) {
        userViewModel.getInfo(userId);
    }

    @Override
    public void getFriendList(String userId, int skip, int limit) {
        userViewModel.getFriendList(userId, skip, limit);
    }
}
