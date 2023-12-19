package com.zh.pregamoms;

public class NewUser {

    private  String email, username, password, name, age, weight, pweek, userId;
    private String title, date, time, place;



        public NewUser() {
        }


    public NewUser(String title, String date, String time, String place) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
    }


    public NewUser(String email, String username, String password, String name, String age, String weight, String pweek, String userId) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.pweek = pweek;
        this.userId = userId;
    }

    public NewUser(String weight, String date) {
        this.weight = weight;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPweek() {
        return pweek;
    }

    public void setPweek(String pweek) {
        this.pweek = pweek;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }




}


