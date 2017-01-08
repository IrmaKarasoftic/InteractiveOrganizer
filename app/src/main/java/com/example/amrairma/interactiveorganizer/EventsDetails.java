package com.example.amrairma.interactiveorganizer;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmWeather;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class EventsDetails extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();

        final String date=getIntent().getStringExtra("date");
        final int pos=getIntent().getIntExtra("pos", 0);

        realm.beginTransaction();
        RealmResults<RealmCalendarEvent> filter=realm.where(RealmCalendarEvent.class).equalTo("date", date).findAll();
        realm.commitTransaction();

        realm.beginTransaction();
        RealmResults<RealmWeather> filter1=realm.where(RealmWeather.class).equalTo("date", date).findAll();
        realm.commitTransaction();


        RealmCalendarEvent day =filter.get(pos-1);
        RealmWeather w = filter1.get(pos-1);
        final TextView eventTitle=(TextView)findViewById(R.id.eventTitle);
        final TextView eventTime=(TextView)findViewById(R.id.eventTime);
        final TextView eventDesc=(TextView)findViewById(R.id.eventDescription);
        final TextView minT = (TextView) findViewById(R.id.minT);
        final TextView maxT = (TextView) findViewById(R.id.maxT);
        final ImageView image = (ImageView) findViewById(R.id.weatherIcon);

        eventTime.setText(day.getTime());
        eventDesc.setText(day.getDescription());
        eventTitle.setText(day.getTitle());
        minT.setText(Double.toString(w.getMinT()));
        maxT.setText(Double.toString(w.getMaxT()));
        if(w.getIcon().equals("snow")) image.setImageResource(R.drawable.snow);
        else if (w.getIcon().equals("sun")) image.setImageResource(R.drawable.sun);
        else if (w.getIcon().equals("rain")) image.setImageResource(R.drawable.rain);
        else image.setImageResource(R.drawable.mist);

    }
}
