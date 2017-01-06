package com.example.amrairma.interactiveorganizer.Models;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("forecast")
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
