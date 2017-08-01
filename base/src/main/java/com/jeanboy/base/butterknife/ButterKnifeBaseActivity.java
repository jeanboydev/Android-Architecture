package com.jeanboy.base.butterknife;

import com.jeanboy.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by jeanboy on 2017/8/1.
 */

public abstract class ButterKnifeBaseActivity extends BaseActivity {

    @Override
    protected void onSetContentView() {
        ButterKnife.bind(this);
    }
}
