package com.jeanboy.app.architecture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jeanboy.app.architecture.R;
import com.jeanboy.base.BaseActivity;
import com.jeanboy.base.utils.ToolBarUtil;
import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.domain.features.login.LoginContract;
import com.jeanboy.domain.features.login.LoginPresenter;
import com.jeanboy.domain.features.user.UserContract;
import com.jeanboy.domain.features.user.UserPresenter;

import java.util.List;

public class LoginActivity extends BaseActivity implements LoginContract.View, UserContract.View {

    private EditText et_username;
    private EditText et_password;

    private LoginPresenter loginPresenter;
    private UserPresenter userPresenter;

    public static void goActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onSetContentView() {
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
        userPresenter = new UserPresenter();
        userPresenter.setView(this);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBarTitle(getToolbar(), "Login");
        ToolBarUtil.setToolbarHomeAsUp(this);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        if (loginPresenter != null) {
            loginPresenter.destroy();
        }
        if (userPresenter != null) {
            userPresenter.destroy();
        }
        super.onDestroy();
    }

    public void toLogin(View view) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        loginPresenter.login(username, password);
    }

    @Override
    public void loginSucceed(TokenModel tokenModel) {
        Log.e(TAG, "===loginSucceed===");
        // TODO: 2017/8/3 通过token获取个人信息
        //模块化的presenter组合使用
        userPresenter.getInfo(tokenModel.getAccessToken(), null);
    }

    @Override
    public void loginError() {
        Log.e(TAG, "===loginError===");
    }

    @Override
    public void getInfoSuccess(UserModel userModel) {
        Log.e(TAG, "===getInfoSuccess===");
    }

    @Override
    public void getInfoError() {
        Log.e(TAG, "===getInfoError===");
    }

    @Override
    public void getFriendListSuccess(List<UserModel> friendList) {

    }

    @Override
    public void getFriendListError() {

    }
}
