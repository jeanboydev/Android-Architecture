package com.jeanboy.app.architecture.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.architecture.R;
import com.jeanboy.app.architecture.base.BindBaseActivity;
import com.jeanboy.base.utils.ToolBarUtil;

public class MainActivity extends BindBaseActivity {

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

    public void openLogin(View view) {
        LoginActivity.goActivity(this);
    }
}
