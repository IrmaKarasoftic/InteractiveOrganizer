package com.example.amrairma.interactiveorganizer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewEventsActivity extends AppCompatActivity {
    ProgressDialog pd;
    ListView lv;
    ArrayList<CalendarEvent> calevents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.view_events);

        EditText selectedDate = (EditText) findViewById(R.id.eventDate);
        /*String day=extras.getString("DAY");
        String month = extras.getString("MONTH");
        String year = extras.getString("YEAR");
        selectedDate.setText(day+"/"+month+"/"+year);*/

        //hard code
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        long date = System.currentTimeMillis();
        String dateString = sdf2.format(date);
        //
        selectedDate.setText(dateString);

        lv = (ListView) findViewById(R.id.listOfEvents);
        //pd = ProgressDialog.show(this, "Loading", "Wait while we get events...");
        if (savedInstanceState == null) PopulateEventsList(calevents);
        ArrayAdapter<CalendarEvent> adapter=new eventAdapter(this,0, calevents);
        lv.setAdapter(adapter);
    }


    void PopulateEventsList(ArrayList<CalendarEvent> events) {
        events.add(new CalendarEvent("titl", "desc1", "18:30", 2, 2, 2));
        events.add(new CalendarEvent("titl2", "desc2", "18:40", 2, 2, 2));
        events.add(new CalendarEvent("titl2", "desc3", "18:30", 2, 2, 2));
        events.add(new CalendarEvent("titl3", "desc4", "18:35", 2, 2, 2));
        events.add(new CalendarEvent("titl4", "desc4", "18:30", 2, 2, 2));
        events.add(new CalendarEvent("titl5", "desc5", "18:30", 2, 2, 2));

    }
}
