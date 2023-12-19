package com.zh.pregamoms.classes;

public class Diet {
    String foodname, date, duration;

    public Diet() {
    }

    public Diet(String foodname, String date) {
        this.foodname = foodname;
        this.date = date;
    }

    public Diet(String foodname, String date, String duration) {
        this.foodname = foodname;
        this.date = date;
        this.duration = duration;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
