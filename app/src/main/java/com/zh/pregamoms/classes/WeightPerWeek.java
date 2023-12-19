package com.zh.pregamoms.classes;

public class WeightPerWeek {
    private String date, weight;

    public WeightPerWeek() {
    }

    public WeightPerWeek(String date, String weight) {
        this.date = date;
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
