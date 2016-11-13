package com.example.amrairma.interactiveorganizer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static android.R.attr.button;

public class addNewEvent extends AppCompatActivity {

    private EditText dsc;
    private EditText eventName;
    private Spinner eventType;
    private TimePicker eventTime;
    private TextView eventDate;
    private Button button;

    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);
        TextView selectedDate=(TextView) findViewById(R.id.selDate);

        long date=System.currentTimeMillis();
        //SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf2.format(date);
        selectedDate.setText(dateString);
        button= (Button) findViewById(R.id.buttonAdd);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dsc= (EditText) findViewById(R.id.description);
                eventName = (EditText) findViewById(R.id.title);
                eventDate = (TextView) findViewById(R.id.selDate); //ovo poslije u date da se moze upisati u bazu
                eventType = (Spinner) findViewById(R.id.spinner);
                eventTime = (TimePicker) findViewById(R.id.tp_timepicker);
                eventTime.setIs24HourView(true);
                if(eventName.getText().toString().length() != 0 && eventType.getSelectedItem().toString().length()!=0) //ovdje jos provjeriti jel setovan time picker) {
                    addEvent();

                else
                {
                    Toast.makeText(addNewEvent.this, " Something is wrong.", Toast.LENGTH_SHORT).show();

                }
            }


    });
    }

    public void addEvent(){

        final String description = dsc.getText().toString().trim();
        final String title=eventName.getText().toString().trim();
        final String type=eventType.getSelectedItem().toString().trim();
        final String datum=eventDate.getText().toString();
        final int sati=eventTime.getCurrentHour();
        final int min=eventTime.getCurrentMinute();
        final String string=datum+" "+sati+":"+min+":00";
        Date dateAndTime=null;
        try{
            dateAndTime=sdf.parse(string);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        final String finalDateAndTime = sdf.format(dateAndTime);
        class AddEvent extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(addNewEvent.this,"Adding...","Wait..",false,false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(addNewEvent.this,s,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(addNewEvent.this, HomePage.class);
                startActivity(intent);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_ENAME,title);
                params.put(Config.KEY_EDESC,description);
                params.put(Config.KEY_EDT, finalDateAndTime);
                params.put(Config.KEY_ETYPE,type);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }

        }

        AddEvent ae= new AddEvent();
        ae.execute();

    }
    // @Override
    public void onClick(View v) {
        if(v == button){
            addEvent();
        }

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

}
