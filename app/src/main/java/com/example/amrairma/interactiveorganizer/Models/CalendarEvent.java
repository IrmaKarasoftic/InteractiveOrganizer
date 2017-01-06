package com.example.amrairma.interactiveorganizer.Models;

public class CalendarEvent {

    private String title;
    private String description;
    private String dateAndTime;
    private int weatherId;
    private int personId;
    private int eventTypeId;


    public CalendarEvent(String title1, String desc, String dt, int w, int p, int e) {
        title = title1;
        description = desc;
        dateAndTime = dt;
        weatherId = w;
        personId = p;
        eventTypeId = e;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDateAndTime() {
        return dateAndTime;
    }
    public int getWeatherId() {
        return weatherId;
    }
    public int getPersonId() {
        return personId;
    }
    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

}
