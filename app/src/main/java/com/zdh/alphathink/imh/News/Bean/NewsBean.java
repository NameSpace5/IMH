package com.zdh.alphathink.imh.News.Bean;

import org.json.JSONArray;

/**
 * Created by Panda on 2016/12/17.
 */

public class NewsBean {
    private JSONArray allList;
    private String PubDate;
    private boolean havePic;
    private String title;
    private String channelName;
    private JSONArray imgUrls;
    private String desc;
    private String source;
    private String link;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHavePic() {
        return havePic;
    }

    public void setHavePic(boolean havePic) {
        this.havePic = havePic;
    }

    public String getPubData() {
        return PubDate;
    }

    public void setPubDate(String pubDate) {
        PubDate = pubDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public JSONArray getAllList() {
        return allList;
    }

    public void setAllList(JSONArray allList) {
        this.allList = allList;
    }

    public JSONArray getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(JSONArray imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
