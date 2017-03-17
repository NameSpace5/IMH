package com.zdh.alphathink.imh.Control.Box;

/**
 * Created by Panda on 2016/11/1.
 */

public class IMHMsg {
    private boolean isInquiry; // true 查询类; false 设置类
    private int type;
    private int id;
    private int color;
    private String name;
    private String time;


    public boolean isInquiry() {
        return isInquiry;
    }

    public void setInquiry(boolean inquiry) {
        isInquiry = inquiry;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public IMHMsg(boolean isInquiry,int type, int id, int color, String name,String time)
            throws IllegalArgumentException {
        this.type = type;
        this.id = id;
        this.color = color;
        this.time = time;
        this.name = name;
        this.isInquiry = isInquiry;
    }
}