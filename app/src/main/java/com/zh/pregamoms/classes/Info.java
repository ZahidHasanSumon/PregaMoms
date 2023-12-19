package com.zh.pregamoms.classes;

public class Info {
    private String BodyChanges, PostPreg, MentalHealth, PhysicalCom, EmergencyCon, ImportantNu, ProhibatedFood,
            Week1;


    public Info() {
    }

    public Info(String week1) {
        Week1 = week1;
    }

    public Info(String bodyChanges, String postPreg) {
        BodyChanges = bodyChanges;
        PostPreg = postPreg;
    }

    public String getWeek1() {
        return Week1;
    }

    public void setWeek1(String week1) {
        Week1 = week1;
    }

    public String getProhibatedFood() {
        return ProhibatedFood;
    }

    public void setProhibatedFood(String prohibatedFood) {
        ProhibatedFood = prohibatedFood;
    }

    public String getBodyChanges() {
        return BodyChanges;
    }

    public void setBodyChanges(String bodyChanges) {
        BodyChanges = bodyChanges;
    }

    public String getPostPreg() {
        return PostPreg;
    }

    public void setPostPreg(String postPreg) {
        PostPreg = postPreg;
    }

    public String getMentalHealth() {
        return MentalHealth;
    }

    public void setMentalHealth(String mentalHealth) {
        MentalHealth = mentalHealth;
    }

    public String getPhysicalCom() {
        return PhysicalCom;
    }

    public void setPhysicalCom(String physicalCom) {
        PhysicalCom = physicalCom;
    }

    public String getEmergencyCon() {
        return EmergencyCon;
    }

    public void setEmergencyCon(String emergencyCon) {
        EmergencyCon = emergencyCon;
    }

    public String getImportantNu() {
        return ImportantNu;
    }

    public void setImportantNu(String importantNu) {
        ImportantNu = importantNu;
    }
}
