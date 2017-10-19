package com.jeanboy.data.cache.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jeanboy.data.cache.database.model.TokenModel;


/**
 * Created by jeanboy on 2017/9/29.
 */

@Dao
public interface TokenModelDao {

    @Query("select * from token limit 1")
    LiveData<TokenModel> get();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TokenModel tokenModel);

    @Delete
    void delete(TokenModel tokenModel);
}
