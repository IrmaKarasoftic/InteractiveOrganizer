package com.example.amrairma.interactiveorganizer;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class addNewEvent extends AppCompatActivity {

    private EditText dsc;
    private EditText eventName;
    private Spinner eventType;
    private TimePicker eventTime;
    private TextView eventDate;
    private Button button;
    private Realm realm;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);
        eventDate = (TextView) findViewById(R.id.selDate);

        long date=System.currentTimeMillis();
        //SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf2.format(date);
        eventDate.setText(dateString);
        button= (Button) findViewById(R.id.buttonAdd);
        dsc= (EditText) findViewById(R.id.description);
        eventName = (EditText) findViewById(R.id.title);
        eventDate = (TextView) findViewById(R.id.selDate); //ovo poslije u date da se moze upisati u bazu
        eventType = (Spinner) findViewById(R.id.spinner);
        eventTime = (TimePicker) findViewById(R.id.tp_timepicker);
        eventTime.setIs24HourView(true);

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(eventName.getText().toString().length() != 0 && eventType.getSelectedItem().toString().length()!=0) //ovdje jos provjeriti jel setovan time picker) {
                {

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, (eventTime.getCurrentHour()));
                    calendar.set(Calendar.MINUTE, (eventTime.getCurrentMinute()));
                    alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(getApplicationContext(), AlarmReciever.class);
                    alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);

                    if (!realm.isInTransaction())
                    realm.beginTransaction();
                    RealmCalendarEvent event=new RealmCalendarEvent();
                    event.setTitle(eventName.getText().toString());
                    event.setDate(eventDate.getText().toString());
                    event.setTime(String.valueOf(eventTime.getCurrentHour()));
                    event.setDescription(dsc.getText().toString());
                    realm.copyToRealm(event);
                    realm.commitTransaction();
                    onBackPressed();

                }
                else
                {
                    Toast.makeText(addNewEvent.this, " Something is wrong.", Toast.LENGTH_SHORT).show();

                }
            }


    });
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }
}
