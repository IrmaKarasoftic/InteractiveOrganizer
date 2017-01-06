package com.example.amrairma.interactiveorganizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.amrairma.interactiveorganizer.Models.ForecastDay;
import com.example.amrairma.interactiveorganizer.Models.Weather;
import com.example.amrairma.interactiveorganizer.Models.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    ApiInterface apiService;
    List<ForecastDay> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final GridView grid = (GridView)findViewById(R.id.grid);
        items = new ArrayList<>();

        WeatherAdapter adapter = new WeatherAdapter(this, R.layout.weather_element, items);
        grid.setAdapter(adapter);

         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiInterface.class);

        Call<Weather> call = apiService.getWeather("5bdb13524fa14f918c9173422161812", "Sarajevo", 7);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                items=response.body().getForecast().getForecastDays();

                WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), R.layout.weather_element, items);
                grid.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });



    }
}
