package com.example.joshuarobertson.sizebook;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by joshuarobertson on 2017-02-04.
 *
 * This is the class that I use to encapsulate data.
 * The two main methods are the Constructor and the AppendToIntent().
 *
 * In the future I would add another constructor to create "blank" DataEntries,
 * for use in the refactoring of Entry.
 *
 * @see Entry
 * @version 1.0
 * @author  jcrobert
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


    /**
     * Instantiates a new Data entry.
     *
     * The lone constructor takes in a bundle and uses it to make a new data entry.
     * Bundles are passed on return from an Activity, so in essence this allows for
     * quick recreation of a data entry from an entry activity, such as EditEntry.
     *
     * @param bundle the bundle
     */
    public DataEntry(Bundle bundle) {
        this.setName(bundle.getString("name"));
        this.setYear(bundle.getString("year"));
        this.setMonth(bundle.getString("month"));
        this.setDay(bundle.getString("day"));
        this.setNeck(bundle.getString("neck"));
        this.setBust(bundle.getString("bust"));
        this.setChest(bundle.getString("chest"));
        this.setWaist(bundle.getString("waist"));
        this.setHip(bundle.getString("hip"));
        this.setInseam(bundle.getString("inseam"));
        this.setComment(bundle.getString("comment"));
    }

    /**
     * Append to intent intent.
     *
     * Intents are how activities communicate.
     * This method allows a data entry to be attached to an intent.
     * Within Entry is a method to quickly extract the data and make
     * a new DataEntry using the DataEntry(Bundle bundle) constructor.
     *
     * @param intent the intent
     * @return the intent
     */
    public Intent AppendToIntent(Intent intent) {

        intent.putExtra("name", this.getName());
        intent.putExtra("day", this.getDay());
        intent.putExtra("month", this.getMonth());
        intent.putExtra("year", this.getYear());
        intent.putExtra("neck", this.getNeck());
        intent.putExtra("bust", this.getBust());
        intent.putExtra("chest", this.getChest());
        intent.putExtra("waist", this.getWaist());
        intent.putExtra("hip", this.getHip());
        intent.putExtra("inseam", this.getInseam());
        intent.putExtra("comment", this.getComment());
        return intent;
    }

    @Override
    public String toString() {
        return this.getName();
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
        this.comment = comment;
    }
}
