package com.jeanboy.app.architecture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jeanboy.app.architecture.R;
import com.jeanboy.base.BaseActivity;
import com.jeanboy.base.utils.ToolsBarUtil;
import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.domain.features.login.LoginContract;
import com.jeanboy.domain.features.login.LoginPresenter;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private EditText et_username;
    private EditText et_password;

    private LoginPresenter loginPresenter;

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
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolsBarUtil.setToolBarTitle(getToolbar(), "Login");
        ToolsBarUtil.setToolbarHomeAsUp(this);
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
    }

    @Override
    public void loginError() {
        Log.e(TAG, "===loginError===");
    }
}
