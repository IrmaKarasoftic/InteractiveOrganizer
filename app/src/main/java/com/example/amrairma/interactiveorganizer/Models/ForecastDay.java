package com.example.amrairma.interactiveorganizer.Models;


import com.google.gson.annotations.SerializedName;

public class ForecastDay {

    @SerializedName("date")
    private String date;
    @SerializedName("day")
    private Day day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
