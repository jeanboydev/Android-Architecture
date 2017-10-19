package com.jeanboy.app.architecture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jeanboy.app.architecture.R;
import com.jeanboy.app.architecture.di.DaggerBaseActivity;
import com.jeanboy.base.utils.ToolBarUtil;
import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.domain.features.token.TokenContract;
import com.jeanboy.domain.features.token.TokenPresenter;
import com.jeanboy.domain.features.user.UserContract;
import com.jeanboy.domain.features.user.UserPresenter;

import java.util.List;

import butterknife.BindView;

public class LoginActivity extends DaggerBaseActivity implements TokenContract.View, UserContract.View {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;

    TokenPresenter tokenPresenter;
    UserPresenter userPresenter;

    public static void goActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBarTitle(getToolbar(), "Login");
        ToolBarUtil.setToolbarHomeAsUp(this);

        tokenPresenter = new TokenPresenter(this);
        tokenPresenter.onViewCreated(this);
        userPresenter = new UserPresenter(this);
        userPresenter.onViewCreated(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void toLogin(View view) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        tokenPresenter.getToken(username, password);
    }

    @Override
    public void onGetTokenSucceed(TokenModel tokenModel) {
        Log.e(TAG, "===onGetTokenSucceed===");
        // TODO: 2017/8/3 通过token获取个人信息
        //模块化的presenter组合使用
        userPresenter.getInfo(tokenModel.getAccessToken(), null);
    }

    @Override
    public void onGetTokenError() {
        Log.e(TAG, "===onGetTokenError===");
    }


    @Override
    public void onInfoChange(UserModel userModel) {
        Log.e(TAG, "===onInfoChange===");
    }

    @Override
    public void onInfoError() {
        Log.e(TAG, "===onInfoError===");
    }

    @Override
    public void onFriendListChange(List<UserModel> friendList) {
        Log.e(TAG, "===onFriendListChange===");
    }

    @Override
    public void onFriendListError() {
        Log.e(TAG, "===onFriendListError===");
    }

}
