package com.jeanboy.app.architecture.di.component;

import android.app.Activity;

import com.jeanboy.app.architecture.di.modules.ActivityModule;
import com.jeanboy.app.architecture.di.scopes.PerActivity;
import com.jeanboy.app.architecture.ui.activity.LoginActivity;
import com.jeanboy.app.architecture.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by jeanboy on 2017/6/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();

    void inject(MainActivity activity);

    void inject(LoginActivity activity);
}
