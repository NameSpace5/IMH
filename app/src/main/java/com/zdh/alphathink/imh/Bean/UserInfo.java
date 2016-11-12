package com.zdh.alphathink.imh.Bean;

/**
 * Created by Panda on 2016/9/13.
 */
public class UserInfo {

    public int uesr_id;//账号
    public int user_password;//密码
    public int user_type;//用户类型
    public String name;//用户名
    public int age;//年龄
    public int phone;//联系方式
    public String sex;//性别
    public UserInfo(){};
    public UserInfo(int uesr_id,int user_password,int user_type,String name,int age,int phone,String sex){
        this.uesr_id = uesr_id;
        this.user_password = user_password;
        this.user_type = user_type;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
