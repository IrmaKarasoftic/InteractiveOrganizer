package com.example.amrairma.interactiveorganizer;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmWeather;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static com.example.amrairma.interactiveorganizer.R.id.email;

public class EventsActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();

        final GridView events=(GridView) findViewById(R.id.events_list);

        int year = getIntent().getIntExtra("year", 0);
        int month = getIntent().getIntExtra("month", 0);
        int day = getIntent().getIntExtra("day", 0);

        String dayy=String.valueOf(day);
        if (dayy.length()==1) dayy="0"+dayy;

        String monthh=String.valueOf(month+1);
        if (monthh.length()==1) monthh="0"+monthh;

        final String date = dayy+"/"+monthh+"/"+String.valueOf(year);
        final TextView minT = (TextView) findViewById(R.id.minT);
        final TextView maxT = (TextView) findViewById(R.id.maxT);
        final ImageView image = (ImageView) findViewById(R.id.weatherIcon);

        realm.beginTransaction();
        RealmResults<RealmCalendarEvent> filter=realm.where(RealmCalendarEvent.class).equalTo("date", date).findAll();
        // RealmResults<RealmCalendarEvent> filter=realm.where(RealmCalendarEvent.class).findAll();
        realm.commitTransaction();

        eventAdapter e = new eventAdapter(this, R.layout.property_layout, filter);
        events.setAdapter(e);

        realm.beginTransaction();
        RealmWeather filter1=realm.where(RealmWeather.class).equalTo("date", date).findFirst();
        realm.commitTransaction();

        if(filter1!=null)
        {
            minT.setText(Double.toString(filter1.getMinT()));
            maxT.setText(Double.toString(filter1.getMaxT()));
            String bla = filter1.getIcon();
            if(filter1.getIcon().toLowerCase().contains("snow")) image.setImageResource(R.drawable.snow);
            else if (filter1.getIcon().toLowerCase().contains("sun")) image.setImageResource(R.drawable.sun);
            else if (filter1.getIcon().toLowerCase().contains("rain")) image.setImageResource(R.drawable.rain);
            else image.setImageResource(R.drawable.mist);
        }
        else
        {
            minT.setText("No data");
            maxT.setText("No data");
            image.setImageResource(R.drawable.no);
        }

    }
}
