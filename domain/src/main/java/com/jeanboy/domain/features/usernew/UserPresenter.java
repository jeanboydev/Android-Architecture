package com.jeanboy.domain.features.usernew;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.handler.RepositoryCallback;


/**
 * Created by jeanboy on 2017/9/30.
 */

public class UserPresenter extends BasePresenterNew<UserContract.View> implements UserContract.PresenterNew {


    public UserPresenter(UserContract.View view) {
        super(view);
    }

    private UserViewModel userViewModel;


    @Override
    public void onViewCreated(LifecycleOwner owner) {
        if (owner instanceof FragmentActivity) {
            userViewModel = ViewModelProviders.of((FragmentActivity) owner).get(UserViewModel.class);
        } else if (owner instanceof Fragment) {
            userViewModel = ViewModelProviders.of((Fragment) owner).get(UserViewModel.class);
        }
        if (userViewModel == null) return;
        userViewModel.getTokenData().observe(owner, new Observer<TokenModel>() {
            @Override
            public void onChanged(@Nullable TokenModel tokenModel) {

            }
        });

        userViewModel.getUserData().observe(owner, new Observer<UserModel>() {
            @Override
            public void onChanged(@Nullable UserModel userModel) {
                view.onInfoChange(userModel);
            }
        });
    }

    @Override
    public void login(String username, String password) {
        userViewModel.login(username, password, new RepositoryCallback<TokenModel>() {
            @Override
            public void onSucceed(TokenModel result) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void getInfo(String accessToken, String userId) {
        userViewModel.getInfo(accessToken, userId, new RepositoryCallback<UserModel>() {
            @Override
            public void onSucceed(UserModel result) {
            }

            @Override
            public void onError(String message) {
                view.onInfoError();
            }
        });
    }

    @Override
    public void getFriendList(String accessToken, String userId, int skip, int limit) {

    }
}
