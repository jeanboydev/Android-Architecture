package com.jeanboy.data.net.entity;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class UserEntity {

    private long id;
    private String username;
    private String userNick;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
}
