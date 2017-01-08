package com.example.amrairma.interactiveorganizer.RealmModels;

import io.realm.RealmObject;

/**
 * Created by Irma on 1/8/2017.
 */

public class RealmWeather extends RealmObject {
    private String date;
    private double minT;
    private double maxT;
    private String description;
    private String icon;

    public RealmWeather() {};


    public String getDate() {
        return date;
    }
    public double getMinT() {
        return minT;
    }
    public double getMaxT() {
        return maxT;
    }
    public String  getDescription() {
        return description;
    }
    public String getIcon() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setMinT(double minT) {
        this.minT = minT;
    }
    public void setMaxT(double maxT) {
        this.maxT = maxT;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
