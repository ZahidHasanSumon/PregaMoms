package com.zh.pregamoms.classes;

public class Exercise {
    String exercise, date, duration;

    public Exercise() {
    }

    public Exercise(String exercise, String date, String duration) {
        this.exercise = exercise;
        this.date = date;
        this.duration = duration;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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
