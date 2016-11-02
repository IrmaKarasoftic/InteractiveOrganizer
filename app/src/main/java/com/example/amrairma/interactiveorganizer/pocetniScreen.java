package com.example.amrairma.interactiveorganizer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class pocetniScreen extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextEmail;

    private Button buttonOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_screen);
        Button button= (Button) findViewById(R.id.buttonOK);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextName=(EditText) findViewById(R.id.firstName);
        editTextLastName=(EditText) findViewById(R.id.lastName);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(pocetniScreen.this, HomePage.class);
                startActivity(intent);
            }
        });
    }


    private void addPerson(){

        final String name = editTextName.getText().toString().trim();
        final String lastname=editTextLastName.getText().toString().trim();
        final String email=editTextEmail.getText().toString().trim();
        // final String birth=

        class AddPerson extends AsyncTask<Void,Void,String> {

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
                Toast.makeText(pocetniScreen.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_NAME,name);
                params.put(Config.KEY_LASTNAME,lastname);
                params.put(Config.KEY_EMAIL,email);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_OK, params);
                return res;
            }

        }

        AddPerson ap= new AddPerson();
        ap.execute();

    }
 //   @Override
    public void onClick(View v) {
        if(v == buttonOK){
            addPerson();
        }

    }
}
