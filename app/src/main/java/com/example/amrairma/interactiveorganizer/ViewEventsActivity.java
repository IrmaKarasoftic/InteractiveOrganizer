package com.example.amrairma.interactiveorganizer;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.amrairma.interactiveorganizer.Models.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewEventsActivity extends AppCompatActivity {
    //ProgressDialog pd;
    //ListView lv;
    //ArrayList<CalendarEvent> calevents = new ArrayList<>();
    ArrayList<HashMap<String,String>> list = new ArrayList<>();

    private ListView listView;
    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_events);
        listView = (ListView) findViewById(R.id.listOfEvents);
        getJSON();

    }

    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute(){

                super.onPreExecute();
                loading = ProgressDialog.show(ViewEventsActivity.this,"Fetching data","Wait..",false,false);

            }
            @Override
            protected void onPostExecute(String s){

                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEvents();
            }
            @Override
            protected String doInBackground(Void... params){

                RequestHandler rh = new RequestHandler();
                String s=rh.sendGetRequest(Config.URL_GET_EVENTS);
                return s;

            }


        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }

    private void showEvents(){

        JSONObject jsonObject = null;
      //  ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try{

            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i=0; i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                String title = jo.getString(Config.TAG_NAME);
                String description = jo.getString(Config.TAG_DESCRIPTION);
               // String dateAndTime=jo.getString(Config.TAG_TIME); //u date
                HashMap<String,String> events = new HashMap<>();
                events.put(Config.TAG_NAME,title);
                events.put(Config.TAG_DESCRIPTION,description);
               // events.put(Config.TAG_TIME,dateAndTime);
                list.add(events);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewEventsActivity.this, list, R.layout.list_item,
                new String[]{Config.TAG_NAME,Config.TAG_DESCRIPTION},//,Config.TAG_TIME},
                new int[]{R.id.eventName, R.id.eventDescription});//,R.id.eventTime});

        listView.setAdapter(adapter);

    }
}
