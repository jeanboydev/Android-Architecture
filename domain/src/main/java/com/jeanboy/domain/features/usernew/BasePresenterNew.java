package com.jeanboy.domain.features.usernew;

import android.arch.lifecycle.LifecycleOwner;

/**
 * Created by jeanboy on 2017/9/30.
 */

public abstract class BasePresenterNew<View> {

    protected View view;

    public BasePresenterNew(View view) {
        this.view = view;
    }

    public abstract void onViewCreated(LifecycleOwner owner);
}
