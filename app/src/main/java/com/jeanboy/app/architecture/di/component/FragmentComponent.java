package com.jeanboy.app.architecture.di.component;

import android.support.v4.app.Fragment;

import com.jeanboy.app.architecture.di.modules.ActivityModule;
import com.jeanboy.app.architecture.di.modules.FragmentModule;
import com.jeanboy.app.architecture.di.scopes.PerActivity;
import com.jeanboy.app.architecture.ui.fragment.SimpleFragment;

import dagger.Component;

/**
 * Created by jeanboy on 2017/6/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FragmentModule.class})
public interface FragmentComponent extends ActivityComponent{

    Fragment fragment();

    void inject(SimpleFragment fragment);
}
