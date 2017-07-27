package com.jeanboy.data.net.impl;

import com.jeanboy.data.net.dao.UserDao;
import com.jeanboy.data.repository.datasource.UserDataSource;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserDaoImpl implements UserDataSource.Remote {

    private UserDao userDao;

    @Override
    public void login(String username, String password) {
        userDao.login(username, password);
    }
}
