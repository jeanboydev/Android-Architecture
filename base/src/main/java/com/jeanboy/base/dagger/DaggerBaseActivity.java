package com.jeanboy.base.dagger;

import com.jeanboy.base.butterknife.ButterKnifeBaseActivity;

/**
 * Created by jeanboy on 2017/8/1.
 */

public abstract class DaggerBaseActivity extends ButterKnifeBaseActivity {

    @Override
    protected void onSetContentView() {
        super.onSetContentView();
        onInject();
    }

    protected abstract void onInject();
}
