package com.jeanboy.app.architecture.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.architecture.R;
import com.jeanboy.base.BaseActivity;
import com.jeanboy.base.utils.ToolsBarUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSetContentView() {

    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolsBarUtil.setToolBarTitle(getToolbar(), "Main");
    }

    @Override
    protected void initData() {

    }

    public void openLogin(View view) {
        LoginActivity.goActivity(this);
    }
}
