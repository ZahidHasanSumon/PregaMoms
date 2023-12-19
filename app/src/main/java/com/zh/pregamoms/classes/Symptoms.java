package com.zh.pregamoms.classes;

public class Symptoms {
    private String Symptoms, Date;

    public Symptoms() {
    }

    public Symptoms(String symptoms, String date) {
        Symptoms = symptoms;
        Date = date;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
