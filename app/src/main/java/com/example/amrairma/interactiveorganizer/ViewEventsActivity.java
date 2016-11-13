package com.example.amrairma.interactiveorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ViewEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.view_events);

        EditText selectedDate = (EditText) findViewById(R.id.eventDate);
        String day=extras.getString("DAY");
        String month = extras.getString("MONTH");
        String year = extras.getString("YEAR");
        selectedDate.setText(day+"/"+month+"/"+year);
    }
    void PopulateEventsList() {
        
    }
}
