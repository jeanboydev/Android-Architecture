package com.jeanboy.base.dagger;

import com.jeanboy.base.butterknife.ButterKnifeBaseFragment;

/**
 * Created by jeanboy on 2017/8/1.
 */

public abstract class DaggerBaseFragment extends ButterKnifeBaseFragment {

    @Override
    protected void onFragmentCreate() {
        onInject();
    }

    protected abstract void onInject();
}
