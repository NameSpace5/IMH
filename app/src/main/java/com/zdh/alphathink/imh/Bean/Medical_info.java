package com.zdh.alphathink.imh.Bean;

import com.zdh.alphathink.imh.Control.ControlActivity;

/**
 * Created by Panda on 2016/10/22.
 */

public class Medical_info {

    private String id;
    private String name;
    private String color;
    private String time;
    private ACache aCache;

    public Medical_info(){
    }
    public void get(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
