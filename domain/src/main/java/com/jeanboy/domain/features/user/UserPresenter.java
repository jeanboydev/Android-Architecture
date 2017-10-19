package com.jeanboy.domain.features.user;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.domain.base.BasePresenter;


/**
 * Created by jeanboy on 2017/9/30.
 */

public class UserPresenter extends BasePresenter<UserContract.View> implements UserContract.Presenter {

    public UserPresenter(UserContract.View view) {
        super(view);
    }

    private UserViewModel userViewModel;

    @Override
    public void onViewCreated(LifecycleOwner owner) {
        userViewModel = getViewModel(owner, UserViewModel.class);
        if (userViewModel == null) return;
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
    public void getInfo(String accessToken, String userId) {
        userViewModel.getInfo(accessToken, userId);
    }

    @Override
    public void getFriendList(String accessToken, String userId, int skip, int limit) {
        userViewModel.getFriendList(accessToken, userId, skip, limit);
    }
}
