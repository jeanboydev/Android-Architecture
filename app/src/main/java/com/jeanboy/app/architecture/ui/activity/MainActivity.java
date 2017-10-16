package com.jeanboy.app.architecture.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.architecture.R;
import com.jeanboy.app.architecture.di.DaggerBaseActivity;
import com.jeanboy.base.utils.ToolBarUtil;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.domain.features.usernew.UserContract;
import com.jeanboy.domain.features.usernew.UserPresenter;

import java.util.List;

public class MainActivity extends DaggerBaseActivity implements UserContract.View {


    private UserPresenter userPresenter;

    @Override
    public void onInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBarTitle(getToolbar(), "Main");
        userPresenter = new UserPresenter(this);
        userPresenter.onViewCreated(this);
    }

    @Override
    protected void initData() {
        userPresenter.getInfo(null);
    }

    private void showUserInfo(UserModel userModel) {

    }

    public void testLogin(String username, String password) {
        userPresenter.login(username, password);
    }

    public void testGetFriendList() {
        userPresenter.getFriendList(null, 0, 10);
    }

    public void openLogin(View view) {
        LoginActivity.goActivity(this);
    }

    @Override
    public void onLoginError() {

    }

    @Override
    public void onInfoChange(UserModel userModel) {
        showUserInfo(userModel);
    }

    @Override
    public void onInfoError() {

    }

    @Override
    public void onFriendListChange(List<UserModel> friendList) {

    }

    @Override
    public void onFriendListError() {

    }
}
