package com.example.amrairma.interactiveorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;

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

        RealmCalendarEvent day =filter.get(pos-1);
        final TextView eventTitle=(TextView)findViewById(R.id.eventTitle);
        final TextView eventTime=(TextView)findViewById(R.id.eventTime);
        final TextView eventDesc=(TextView)findViewById(R.id.eventDescription);

        eventTime.setText(day.getTime());
        eventDesc.setText(day.getDescription());
        eventTitle.setText(day.getTitle());


    }
}
