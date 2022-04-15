package com.codingstuff.customtablayout;

public class newsmodel {
    String title,desc,time,source,image;

    public newsmodel(String title, String desc, String time, String source, String image) {
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.source = source;
        this.image = image;
    }

    public newsmodel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
