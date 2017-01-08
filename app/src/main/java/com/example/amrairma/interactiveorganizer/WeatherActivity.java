package com.example.amrairma.interactiveorganizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.amrairma.interactiveorganizer.Models.ForecastDay;
import com.example.amrairma.interactiveorganizer.Models.Weather;
import com.example.amrairma.interactiveorganizer.Models.WeatherAdapter;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmPerson;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmWeather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    ApiInterface apiService;
    List<ForecastDay> items;
    private Realm realm;

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

        Call<Weather> call = apiService.getWeather("5bdb13524fa14f918c9173422161812", "Sarajevo", 30);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                items=response.body().getForecast().getForecastDays();

                WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), R.layout.weather_element, items);
                grid.setAdapter(adapter);


                for (int i=0; i<items.size();i++)
                {
                    String date=items.get(i).getDate();
                    String date1 =items.get(i).getDate();
                    DateFormat inputd = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat outputd = new SimpleDateFormat("dd/MM/yyyy");

                    try {
                        Date date2=inputd.parse(date1);
                        date = outputd.format(date2).toString();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    Realm.init(getApplicationContext());

                    RealmConfiguration config = new RealmConfiguration.Builder()
                            .deleteRealmIfMigrationNeeded()
                            .build();
                    Realm.setDefaultConfiguration(config);
                    realm = Realm.getDefaultInstance();

                    realm.beginTransaction();
                    RealmResults<RealmWeather> filter=realm.where(RealmWeather.class).equalTo("date", date).findAll();
                    realm.commitTransaction();

                    if (filter.size()==0)
                    {
                        if (!realm.isInTransaction())
                            realm.beginTransaction();
                        RealmWeather weather=new RealmWeather();
                        weather.setDate(date);
                        weather.setMaxT(items.get(i).getDay().getMaxTemp());
                        weather.setMinT(items.get(i).getDay().getMinTemp());
                        weather.setDescription(items.get(i).getDay().getCondition().getText());
                        weather.setIcon(items.get(i).getDay().getCondition().getIcon());
                        realm.copyToRealm(weather);
                        realm.commitTransaction();
                    }
                }
                RealmResults<RealmWeather> all=realm.where(RealmWeather.class).findAll();

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });



    }
}
