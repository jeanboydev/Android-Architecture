package com.jeanboy.data.cache.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jeanboy.data.cache.database.dao.DaoMaster;
import com.jeanboy.data.cache.database.dao.UserModelDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by jeanboy on 2017/5/22.
 */

public class MyDevOpenHelper extends DaoMaster.DevOpenHelper {

    public MyDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");
        MigrationHelper.getInstance().migrate(db, UserModelDao.class);
    }
}
