package com.jeanboy.app.architecture.base;

import com.jeanboy.app.architecture.di.DaggerApplication;
import com.jeanboy.data.cache.manager.GreenDaoHandler;
import com.jeanboy.data.cache.manager.GreenDaoManager;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class MainApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        setup();
    }

    private void setup() {
        GreenDaoManager.getInstance().build(getApplicationContext(), new GreenDaoHandler());
    }

}
