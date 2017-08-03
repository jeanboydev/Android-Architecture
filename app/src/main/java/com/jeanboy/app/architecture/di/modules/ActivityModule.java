package com.jeanboy.app.architecture.di.modules;

import android.app.Activity;

import com.jeanboy.app.architecture.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeanboy on 2017/6/19.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}
