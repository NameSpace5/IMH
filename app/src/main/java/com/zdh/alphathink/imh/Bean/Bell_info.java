package com.zdh.alphathink.imh.Bean;

/**
 * Created by Panda on 2016/12/3.
 */

public class Bell_info {
    private String name;
    private String description;
    private String remark;
    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Bell_info(){};

    public Bell_info(int _id,String name, String description, String remark){
        this.description = description;
        this.name = name;
        this.remark = remark;
        this._id = _id;

    }
}
