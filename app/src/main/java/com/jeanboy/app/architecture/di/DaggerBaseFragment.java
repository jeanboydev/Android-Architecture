package com.jeanboy.app.architecture.di;

import com.jeanboy.app.architecture.base.BindBaseFragment;
import com.jeanboy.app.architecture.di.component.ApplicationComponent;
import com.jeanboy.app.architecture.di.component.DaggerFragmentComponent;
import com.jeanboy.app.architecture.di.component.FragmentComponent;
import com.jeanboy.app.architecture.di.modules.ActivityModule;
import com.jeanboy.app.architecture.di.modules.FragmentModule;
import com.jeanboy.base.wrapper.DiWrapper;

/**
 * Created by jeanboy on 2017/8/2.
 */

public abstract class DaggerBaseFragment extends BindBaseFragment implements DiWrapper {

    private FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    private ActivityModule getActivityModule() {
        return new ActivityModule(getActivity());
    }

    private ApplicationComponent getApplicationComponent() {
        return ((DaggerApplication) getContext().getApplicationContext()).getApplicationComponent();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder().applicationComponent(getApplicationComponent()).activityModule
                (getActivityModule()).fragmentModule(getFragmentModule()).build();
    }

    @Override
    protected void onFragmentCreate() {
        super.onFragmentCreate();
        onInject();
    }

    @Override
    public void onInject() {
        getApplicationComponent().inject(this);
    }
}
