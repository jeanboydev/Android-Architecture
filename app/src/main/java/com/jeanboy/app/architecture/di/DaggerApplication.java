package com.jeanboy.app.architecture.di;

import android.app.Application;

import com.jeanboy.app.architecture.di.component.ApplicationComponent;
import com.jeanboy.app.architecture.di.component.DaggerApplicationComponent;
import com.jeanboy.app.architecture.di.modules.ApplicationModule;

/**
 * Created by jeanboy on 2017/8/2.
 */

public class DaggerApplication extends Application {

    private static DaggerApplication instance;

    public static DaggerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule
                (getApplicationContext())).build();
    }

    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
