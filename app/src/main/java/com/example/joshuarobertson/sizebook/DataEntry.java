package com.example.joshuarobertson.sizebook;

/**
 * Created by joshuarobertson on 2017-02-04.
 */
public class DataEntry {

    private String name;
    private String year;
    private String month;
    private String day;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    public DataEntry(String name) {
        this.name = name;
        this.year = "";
        this.month = "";
        this.day = "";
        this.neck = "";
        this.bust = "";
        this.chest = "";
        this.waist = "";
        this.hip = "";
        this.inseam = "";
        this.comment = "";
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void setDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setMeasurements(String neck, String bust, String chest, String waist, String hip, String inseam) {
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        comment = comment;
    }
}