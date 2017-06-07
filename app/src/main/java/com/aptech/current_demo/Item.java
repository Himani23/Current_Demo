package com.aptech.current_demo;

/**
 * Created by Administrator on 24-04-2017.
 */

public class Item {

    String title,description,cdate,stipend;

    public Item(String title, String description,String cdate) {
        this.title = title;
        this.description = description;
        this.cdate = cdate;
    }
    public Item(String title, String description,String cdate,String stipend) {
        this.title = title;
        this.description = description;
        this.cdate = cdate;
        this.stipend = stipend;
    }



    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getStipend() {
        return stipend;
    }

    public void setStipend(String stipend) {
        this.stipend = stipend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
