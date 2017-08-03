package com.jeanboy.app.architecture.di.modules;

import android.support.v4.app.Fragment;

import com.jeanboy.app.architecture.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jeanboy on 2017/6/19.
 */
@Module
public class FragmentModule {

    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerActivity
    Fragment provideFragment() {
        return fragment;
    }
}
