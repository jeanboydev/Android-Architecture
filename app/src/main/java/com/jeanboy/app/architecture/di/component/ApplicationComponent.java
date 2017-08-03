package com.jeanboy.app.architecture.di.component;

import android.content.Context;

import com.jeanboy.app.architecture.di.modules.ApplicationModule;
import com.jeanboy.base.BaseActivity;
import com.jeanboy.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jeanboy on 2017/6/19.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);
}
