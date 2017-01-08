package com.example.amrairma.interactiveorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class EventsActivity extends AppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        getSupportActionBar().setTitle("Events");

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

        realm.beginTransaction();
        RealmResults<RealmCalendarEvent> filter=realm.where(RealmCalendarEvent.class).equalTo("date", date).findAll();
       // RealmResults<RealmCalendarEvent> filter=realm.where(RealmCalendarEvent.class).findAll();

        realm.commitTransaction();

        eventAdapter e = new eventAdapter(this, R.layout.property_layout, filter);
        events.setAdapter(e);

        events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "klix", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), EventsDetails.class);
                intent.putExtra("date", date);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

    }
}
