package com.jeanboy.app.architecture.base;

import android.app.Application;

import com.jeanboy.data.cache.manager.GreenDaoHandler;
import com.jeanboy.data.cache.manager.GreenDaoManager;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setup();
    }

    private void setup() {
        GreenDaoManager.getInstance().build(getApplicationContext(), new GreenDaoHandler());
    }
}
