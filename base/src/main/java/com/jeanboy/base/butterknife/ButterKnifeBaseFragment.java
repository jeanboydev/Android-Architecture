package com.jeanboy.base.butterknife;

import android.os.Bundle;
import android.view.View;

import com.jeanboy.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jeanboy on 2017/8/1.
 */

public abstract class ButterKnifeBaseFragment extends BaseFragment {

    private Unbinder unbinder;

    @Override
    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}
