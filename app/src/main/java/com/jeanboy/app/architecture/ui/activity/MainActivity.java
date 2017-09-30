package com.jeanboy.app.architecture.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jeanboy.app.architecture.R;
import com.jeanboy.app.architecture.di.DaggerBaseActivity;
import com.jeanboy.base.utils.ToolBarUtil;
import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.domain.features.usernew.UserViewModel;

public class MainActivity extends DaggerBaseActivity {

    private UserViewModel userViewModel;

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

        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);

    }

    @Override
    protected void initData() {
        userViewModel.login("","'");
        userViewModel.getTokenData().observe(this, new Observer<TokenModel>() {
            @Override
            public void onChanged(@Nullable TokenModel tokenModel) {

            }
        });
    }

    public void openLogin(View view) {
        LoginActivity.goActivity(this);
    }
}
