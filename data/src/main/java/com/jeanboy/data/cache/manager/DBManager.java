package com.jeanboy.data.cache.manager;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by jeanboy on 2016/12/9.
 */

public class DBManager {

    private Context context;
    private String dbName;
    private AppDatabase appDatabase;

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static DBManager instance = new DBManager();
    }

    private DBManager() {
    }

    public static DBManager getInstance() {
        return SingletonHolder.instance;
    }

    public void build(Context context, String dbName) {
        this.context = context.getApplicationContext();
        this.dbName = dbName;
    }

    public AppDatabase getDataBase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName).build();
        }
        return appDatabase;
    }

    public void toMigration() {
        Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                        + "`name` TEXT, PRIMARY KEY(`id`))");
            }
        };
    }
}
