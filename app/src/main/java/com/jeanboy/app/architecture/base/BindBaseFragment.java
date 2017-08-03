package com.jeanboy.app.architecture.base;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.base.BaseFragment;
import com.jeanboy.base.wrapper.BindWrapper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2017/8/3.
 */

public abstract class BindBaseFragment extends BaseFragment implements BindWrapper {

    private Unbinder unbinder;

    @Override
    protected void onFragmentCreate() {
        // TODO: 2017/8/3 nothing
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
    public void onBind(Object target) {
        unbinder = ButterKnife.bind((View) target);
    }

    @Override
    public void onUnbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
