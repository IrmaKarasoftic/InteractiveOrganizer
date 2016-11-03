package com.example.amrairma.interactiveorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class pocetniScreen extends AppCompatActivity {

    private static final Pattern sPattern
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_screen);
        Button button= (Button) findViewById(R.id.buttonOK);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText firstName = (EditText) findViewById(R.id.firstName);
                EditText lastName = (EditText) findViewById(R.id.lastName);
                EditText email = (EditText) findViewById(R.id.email);
                boolean isEmailValid= Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();

                if(firstName.getText().toString().length() != 0 && lastName.getText().toString().length()!=0 && isEmailValid) {
                    Intent intent = new Intent(pocetniScreen.this, HomePage.class);
                    startActivity(intent);
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
}
