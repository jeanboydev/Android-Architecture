package com.jeanboy.data.cache.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jeanboy on 2017/7/27.
 */
@Entity
public class UserModel {

    @Id(autoincrement = true)
    private Long id;
    private String userName;
    private String userNick;
    private Long createTime;

    @Generated(hash = 1474124873)
    public UserModel(Long id, String userName, String userNick, Long createTime) {
        this.id = id;
        this.userName = userName;
        this.userNick = userNick;
        this.createTime = createTime;
    }

    @Generated(hash = 782181818)
    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
