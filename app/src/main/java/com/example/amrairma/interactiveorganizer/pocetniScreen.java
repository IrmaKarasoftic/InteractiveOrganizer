package com.example.amrairma.interactiveorganizer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrairma.interactiveorganizer.Models.Config;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;
import com.example.amrairma.interactiveorganizer.RealmModels.RealmPerson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class pocetniScreen extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private String JSON_STRING;
    private Realm realm;
    Integer brojac = null;

    private static final Pattern sPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
    private Button buttonOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_screen);
        realm.beginTransaction();
        RealmResults<RealmPerson> filter=realm.where(RealmPerson.class).findAll();
        realm.commitTransaction();
        if (filter.size() != 0)
        {
            Intent intent = new Intent(pocetniScreen.this, HomePage.class);
            startActivity(intent);
        }
        Button button= (Button) findViewById(R.id.buttonOK);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextName=(EditText) findViewById(R.id.firstName);
        editTextLastName=(EditText) findViewById(R.id.lastName);

       button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText firstName = (EditText) findViewById(R.id.firstName);
                EditText lastName = (EditText) findViewById(R.id.lastName);
                EditText email = (EditText) findViewById(R.id.email);
                boolean isEmailValid= Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();

                if(firstName.getText().toString().length() != 0 && lastName.getText().toString().length()!=0 && isEmailValid) {
                    addPerson();
                }
                else
                {
                    if (isEmailValid)
                    {
                        Toast.makeText(pocetniScreen.this, "You need to enter your credentials.", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(pocetniScreen.this, "You need to enter valid email.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void addPerson(){

        final String name = editTextName.getText().toString().trim();
        final String lastname=editTextLastName.getText().toString().trim();
        final String email=editTextEmail.getText().toString().trim();
        // final String birth=

        class AddPerson extends AsyncTask<Void,Void,String> {
            private Realm realm;
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(pocetniScreen.this,"Adding...","Wait..",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Intent intent = new Intent(pocetniScreen.this, HomePage.class);
                startActivity(intent);
            }

            @Override
            protected String doInBackground(Void... v) {
                Realm.init(getApplicationContext());

                RealmConfiguration config = new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();
                Realm.setDefaultConfiguration(config);
                realm = Realm.getDefaultInstance();

                if (!realm.isInTransaction())
                    realm.beginTransaction();
                RealmPerson person=new RealmPerson();
                person.setFirstName(name);
                person.setLastName(lastname);
                person.setEmail(email);

                realm.copyToRealm(person);
                realm.commitTransaction();
                /*HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_NAME,name);
                params.put(Config.KEY_LASTNAME,lastname);
                params.put(Config.KEY_EMAIL,email);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_OK, params);
                return res;*/
                return "Success";
            }

        }

        AddPerson ap= new AddPerson();
        ap.execute();

    }
 // @Override
    public void onClick(View v) {
        if(v == buttonOK){
            addPerson();
        }

    }

    public void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute(){

                super.onPreExecute();
                loading = ProgressDialog.show(pocetniScreen.this,"Fetching data","Wait..",false,false);

            }
            @Override
            protected void onPostExecute(String s){

                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showNumber();
            }
            @Override
            protected String doInBackground(Void... params){

                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequest(Config.URL_GET_PERSON);

            }


        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }

    private void showNumber(){

        JSONObject jsonObject = null;
        //  ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try{

            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

           // for(int i=0; i<result.length();i++){
                JSONObject jo = result.getJSONObject(0);
                brojac=jo.getInt(Config.TAG_BROJAC);
                //String title = jo.getString(Config.TAG_NAME);
               // String description = jo.getString(Config.TAG_DESCRIPTION);
                // String dateAndTime=jo.getString(Config.TAG_TIME); //u date
               // HashMap<String,String> events = new HashMap<>();
               // events.put(Config.TAG_NAME,title);
               // events.put(Config.TAG_DESCRIPTION,description);
                // events.put(Config.TAG_TIME,dateAndTime);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
