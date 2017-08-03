package com.jeanboy.app.architecture.di;

import com.jeanboy.app.architecture.base.BindBaseActivity;
import com.jeanboy.app.architecture.di.component.ActivityComponent;
import com.jeanboy.app.architecture.di.component.ApplicationComponent;
import com.jeanboy.app.architecture.di.component.DaggerActivityComponent;
import com.jeanboy.app.architecture.di.modules.ActivityModule;
import com.jeanboy.base.wrapper.DiWrapper;

/**
 * Created by jeanboy on 2017/8/2.
 */

public abstract class DaggerBaseActivity extends BindBaseActivity implements DiWrapper {

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

    @Override
    protected void onSetContentView() {
        super.onSetContentView();
        onInject();
    }

    @Override
    public void onInject() {
        getApplicationComponent().inject(this);
    }
}