package com.zdh.alphathink.imh.Personal.UserDao;

import cn.bmob.v3.BmobObject;

/**
 * Created by Panda on 2017/3/3.
 */

public class User extends BmobObject{
    private String phoneNumber;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
