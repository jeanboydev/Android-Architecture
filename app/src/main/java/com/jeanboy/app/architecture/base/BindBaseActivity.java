package com.jeanboy.app.architecture.base;

import android.app.Activity;

import com.jeanboy.base.BaseActivity;
import com.jeanboy.base.wrapper.BindWrapper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2017/8/3.
 */

public abstract class BindBaseActivity extends BaseActivity implements BindWrapper {

    private Unbinder unbinder;

    @Override
    protected void onSetContentView() {
        onBind(this);
    }

    @Override
    protected void onDestroy() {
        onUnbind();
        super.onDestroy();
    }

    @Override
    public void onBind(Object target) {
        unbinder = ButterKnife.bind((Activity) target);
    }

    @Override
    public void onUnbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
