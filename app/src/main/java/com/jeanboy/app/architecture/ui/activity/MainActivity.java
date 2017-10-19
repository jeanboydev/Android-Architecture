package com.jeanboy.app.architecture.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.architecture.R;
import com.jeanboy.app.architecture.di.DaggerBaseActivity;
import com.jeanboy.base.utils.ToolBarUtil;
import com.jeanboy.data.cache.database.model.UserModel;

public class MainActivity extends DaggerBaseActivity {


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
    }

    @Override
    protected void initData() {
    }

    private void showUserInfo(UserModel userModel) {

    }

    public void openLogin(View view) {
        LoginActivity.goActivity(this);
    }
}
