package com.zdh.alphathink.imh.News;

import android.graphics.Bitmap;

/**
 * Created by Panda on 2016/12/2.
 */

public class NewsStyleMessage {
    private int type;//指定是哪种类型
    private String title;//新闻标题
    private String content;//新闻内容
    private int pic;//新闻图片
    private int tag;
    private Bitmap pic_net;
    private String url;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getTag() {
        return tag;
    }
    public void setTag(int tag) {
        this.tag = tag;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPic() {
        return pic;
    }
    public void setPic(int pic) {
        this.pic = pic;
    }
    public Bitmap getPicNet() {
        return pic_net;
    }
    public void setPicNet(Bitmap pic_net) {
        this.pic_net = pic_net;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
