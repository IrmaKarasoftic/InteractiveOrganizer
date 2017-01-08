package com.example.amrairma.interactiveorganizer.RealmModels;

import io.realm.RealmObject;

public class RealmCalendarEvent extends RealmObject {

    private String title;
    private String description;
    private String date;
    private String time;
    private String type;

    public RealmCalendarEvent() {};


    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
