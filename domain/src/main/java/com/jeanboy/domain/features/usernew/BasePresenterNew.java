package com.jeanboy.domain.features.usernew;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by jeanboy on 2017/9/30.
 */

public abstract class BasePresenterNew<View> {

    protected View view;

    public BasePresenterNew(View view) {
        this.view = view;
    }

    public <T extends ViewModel> T getViewModel(LifecycleOwner owner, Class<T> clazz) {
        if (owner instanceof FragmentActivity) {
            return ViewModelProviders.of((FragmentActivity) owner).get(clazz);
        } else if (owner instanceof Fragment) {
            return ViewModelProviders.of((Fragment) owner).get(clazz);
        }
        return null;
    }

    public abstract void onViewCreated(LifecycleOwner owner);
}
