package com.jeanboy.data.cache.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.datasource.UserDataSourceNew;

import java.util.List;


/**
 * Created by jeanboy on 2017/9/29.
 */

@Dao
public interface UserModelDao extends UserDataSourceNew.Local {

    @Query("select * from user where id = :userId")
    LiveData<UserModel> getById(String userId);

    @Query("select * from user")
    @Override
    LiveData<List<UserModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Override
    void insert(UserModel userModel);

    @Delete
    @Override
    void delete(UserModel userModel);
}
