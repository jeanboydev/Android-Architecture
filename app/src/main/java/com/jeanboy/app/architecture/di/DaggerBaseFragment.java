package com.jeanboy.app.architecture.di;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.architecture.di.component.ApplicationComponent;
import com.jeanboy.app.architecture.di.component.DaggerFragmentComponent;
import com.jeanboy.app.architecture.di.component.FragmentComponent;
import com.jeanboy.app.architecture.di.modules.ActivityModule;
import com.jeanboy.app.architecture.di.modules.FragmentModule;
import com.jeanboy.base.BaseFragment;
import com.jeanboy.base.wrapper.BindWrapper;
import com.jeanboy.base.wrapper.DiWrapper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2017/8/2.
 */

public abstract class DaggerBaseFragment extends BaseFragment implements DiWrapper, BindWrapper {

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

    private Unbinder unbinder;

    @Override
    protected void onFragmentCreate() {
        onInject();
    }

    @Override
    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        onBind(view);
    }

    @Override
    public void onDestroyView() {
        onUnbind();
        super.onDestroyView();
    }

    @Override
    public void onInject() {
        getApplicationComponent().inject(this);
    }

    @Override
    public void onBind(View target) {
        unbinder = ButterKnife.bind(target);
    }

    @Override
    public void onUnbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
