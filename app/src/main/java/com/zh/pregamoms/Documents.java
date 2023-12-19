package com.zh.pregamoms;

public class Documents {
    private String dTitle, dDate, bitmap;

    public Documents() {
    }

    public Documents(String dTitle, String dDate, String bitmap) {
        this.dTitle = dTitle;
        this.dDate = dDate;
        this.bitmap = bitmap;
    }

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }
}
