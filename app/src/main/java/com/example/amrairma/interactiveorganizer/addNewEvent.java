package com.example.amrairma.interactiveorganizer;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmEventTypes;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmMailToPersons;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmPerson;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmWeather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.jar.Attributes;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.R.attr.path;

public class addNewEvent extends AppCompatActivity {

    private EditText dsc;
    private EditText eventName;
    private EditText Name1;
    private EditText Name2;
    private EditText Name3;
    private EditText mail1;
    private EditText mail2;
    private EditText mail3;

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
        eventType = (Spinner) findViewById(R.id.type);
        eventTime = (TimePicker) findViewById(R.id.tp_timepicker);
        eventTime.setIs24HourView(true);
        Name1=(EditText)findViewById(R.id.mailToName1);
        Name2=(EditText)findViewById(R.id.mailToName2);
        Name3=(EditText)findViewById(R.id.mailToName3);

        mail1=(EditText)findViewById(R.id.mailTo1);
        mail2=(EditText)findViewById(R.id.mailTo2);
        mail3=(EditText)findViewById(R.id.mailTo3);

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        RealmResults<RealmEventTypes> eventTypes=realm.where(RealmEventTypes.class).findAll();
        realm.commitTransaction();
        if(eventTypes.size()==0)
        {
            RealmEventTypes ret1=new RealmEventTypes();
            RealmEventTypes ret2=new RealmEventTypes();
            RealmEventTypes ret3=new RealmEventTypes();

            realm.beginTransaction();
            ret1.setDescription("Rodjendan");
            ret1.setColor("#4286f4");
            realm.copyToRealm(ret1);
            realm.commitTransaction();

            realm.beginTransaction();
            ret2.setDescription("Sastanak");
            ret2.setColor("#9b42f4");
            realm.copyToRealm(ret2);
            realm.commitTransaction();

            realm.beginTransaction();
            ret3.setDescription("Sastanak");
            ret3.setColor("#f46542");
            realm.copyToRealm(ret1);
            realm.commitTransaction();
        }

        final String[] selectedItemText = new String[1];
        eventType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText[0] = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                    alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);

                    alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);

                    if (!realm.isInTransaction())
                    realm.beginTransaction();
                    RealmCalendarEvent event=new RealmCalendarEvent();
                    event.setTitle(eventName.getText().toString());
                    event.setDate(eventDate.getText().toString());
                    String hour=eventTime.getCurrentHour().toString();
                    String minute=eventTime.getCurrentMinute().toString();
                    String time = hour+':'+minute;

                    event.setTime(time);
                    event.setType(selectedItemText[0]);
                    event.setDescription(dsc.getText().toString());
                    RealmList<RealmMailToPersons> persons = new RealmList<>();
                    RealmMailToPersons rmtp1= new RealmMailToPersons();
                    rmtp1.setMail(mail1.toString());
                    rmtp1.setMail(Name1.toString());
                    persons.add(rmtp1);
                    RealmMailToPersons rmtp2= new RealmMailToPersons();
                    RealmMailToPersons rmtp3= new RealmMailToPersons();
                    rmtp2.setMail(mail1.toString());
                    rmtp2.setMail(Name1.toString());
                    persons.add(rmtp2);
                    rmtp3.setMail(mail1.toString());
                    rmtp3.setMail(Name1.toString());
                    persons.add(rmtp3);
                    event.setPersons(persons);
                    realm.copyToRealm(event);
                    realm.commitTransaction();
                    if(mail1.getText().toString()!="" && mail2.getText().toString()!="" && mail3.getText().toString()!="")
                    {
                        String[] addresses = {
                                mail1.getText().toString(),mail2.getText().toString(),mail3.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello! Amra and Irma sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }

                    else if(mail1.getText().toString()!="" && mail2.getText().toString()!="" && mail3.getText().toString()=="")
                    {
                        String[] addresses = {
                                mail1.getText().toString(),mail2.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello! Someone sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }

                    else if(mail1.getText().toString()!="" && mail2.getText().toString()=="" && mail3.getText().toString()!="")
                    {
                        String[] addresses = {
                                mail1.getText().toString(),mail3.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello!. Someone sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                    else if(mail1.getText().toString()=="" && mail2.getText().toString()!="" && mail3.getText().toString()!="")
                    {
                        String[] addresses = {
                                mail2.getText().toString(),mail3.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello!. Someone sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                    else if(mail1.getText().toString()!="" && mail2.getText().toString()=="" && mail3.getText().toString()=="")
                    {
                        String[] addresses = {
                                mail1.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello " + Name1.toString()+". Someone sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                    else if(mail1.getText().toString()=="" && mail2.getText().toString()!="" && mail3.getText().toString()=="")
                    {
                        String[] addresses = {
                                mail2.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello " +Name2.toString()+". Someone sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }

                    else if(mail1.getText().toString()=="" && mail2.getText().toString()=="" && mail3.getText().toString()!="")
                    {
                        String[] addresses = {
                                mail3.getText().toString()
                        };
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);
                        String message = "Hello " +Name3.toString()+". Someone sent you details about one event. Event title: "+event.getTitle() + " ("+event.getDescription()+") will be on: "+event.getDate() + " at: "+ event.getTime();
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        try {
                            startActivity(Intent.createChooser(emailIntent,
                                    "Send email using..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                        }
                    }
                    onBackPressed();
                }
                else
                {
                    Toast.makeText(addNewEvent.this, "You must enter all informations", Toast.LENGTH_SHORT).show();

                }
            }


    });
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }
}
