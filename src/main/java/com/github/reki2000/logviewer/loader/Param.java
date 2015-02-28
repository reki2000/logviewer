package com.github.reki2000.logviewer.loader;

public class Param {
    int year;
    int month;
    int day;
    String keyword;

    public Param(int year, int month, int day, String keyword) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.keyword = keyword;
    }

    public Param(String yyyymmdd, String keyword) {
        this.year = Integer.parseInt(yyyymmdd.substring(0,4));
        this.month = Integer.parseInt(yyyymmdd.substring(4,6));
        this.day = Integer.parseInt(yyyymmdd.substring(6,8));
        this.keyword = keyword;
    }

    public String getYYYY() {
        return String.format("%04d", year);
    }

    public String getMM() {
        return String.format("%02d", month);
    }

    public String getDD() {
        return String.format("%02d", day);
    }
}
