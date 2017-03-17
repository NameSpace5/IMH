package com.zdh.alphathink.imh.Personal.UserDao;

import cn.bmob.v3.BmobObject;

/**
 * Created by Panda on 2017/3/17.
 */

public class User extends BmobObject {
    private String nickname;
    private String phoneNumber;
    private String password;

    public User(){
        this.setTableName("table_user");
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
