package com.jeanboy.app.architecture.di;

import android.view.View;

import com.jeanboy.app.architecture.di.component.ActivityComponent;
import com.jeanboy.app.architecture.di.component.ApplicationComponent;
import com.jeanboy.app.architecture.di.component.DaggerActivityComponent;
import com.jeanboy.app.architecture.di.modules.ActivityModule;
import com.jeanboy.base.BaseActivity;
import com.jeanboy.base.wrapper.BindWrapper;
import com.jeanboy.base.wrapper.DiWrapper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2017/8/2.
 */

public abstract class DaggerBaseActivity extends BaseActivity implements DiWrapper, BindWrapper {

    private ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((DaggerApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder().applicationComponent(getApplicationComponent()).activityModule
                (getActivityModule()).build();
    }

    private Unbinder unbinder;

    @Override
    protected void onSetContentView() {
        onBind(getWindow().getDecorView());
        onInject();
    }

    @Override
    protected void onDestroy() {
        onUnbind();
        super.onDestroy();
    }

    @Override
    public void onInject() {
        getApplicationComponent().inject(this);
    }

    @Override
    public void onBind(View target) {
        unbinder = ButterKnife.bind(target);
    }

    @Override
    public void onUnbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}