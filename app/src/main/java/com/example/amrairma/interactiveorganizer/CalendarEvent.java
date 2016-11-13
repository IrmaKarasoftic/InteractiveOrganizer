package com.example.amrairma.interactiveorganizer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

import hirondelle.date4j.DateTime;

/**
 * Created by irmaka on 11/14/2016.
 */
public class CalendarEvent {
    public static final Parcelable.Creator<CalendarEvent> CREATOR = new Parcelable.Creator<CalendarEvent>() {
        public CalendarEvent createFromParcel(Parcel in) {
            return new CalendarEvent(in);
        }

        public CalendarEvent[] newArray(int size) {
            return new CalendarEvent[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString( description);
        dest.writeString(dateAndTime);
        dest.writeInt(weatherId);
        dest.writeInt(personId);
        dest.writeInt(eventTypeId);
    }

    public CalendarEvent(Parcel dest) {
        title = dest.readString();
        description = dest.readString();
        dateAndTime = dest.readString();
        weatherId = dest.readInt();
        personId = dest.readInt();
        eventTypeId = dest.readInt();
    }

    private String title;
    private String description;
    private String dateAndTime;
    private int weatherId;
    private int personId;
    private int eventTypeId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }
    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getWeatherId() {
        return weatherId;
    }
    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }
    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
