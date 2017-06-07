package com.aptech.current_demo;

/**
 * Created by Administrator on 28-04-2017.
 */

public class Jobs {

    String title,salary,exper,skills,location,farea,company,email;

    public Jobs(String title, String salary, String exper, String skills, String location, String farea,String company,String email) {
        this.title = title;
        this.salary = salary;
        this.exper = exper;
        this.skills = skills;
        this.location = location;
        this.farea = farea;
        this.company = company;
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getExper() {
        return exper;
    }

    public void setExper(String exper) {
        this.exper = exper;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFarea() {
        return farea;
    }

    public void setFarea(String farea) {
        this.farea = farea;
    }
}
