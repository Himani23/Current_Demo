package com.aptech.current_demo;

/**
 * Created by Administrator on 26-05-2017.
 */

public class News {
    String title,desc,imagepath,date;
    int id;

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

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public News(String title, String desc, String imagepath, String date, int id) {

        this.title = title;
        this.desc = desc;
        this.imagepath = imagepath;
        this.date = date;
        this.id = id;
    }
}
