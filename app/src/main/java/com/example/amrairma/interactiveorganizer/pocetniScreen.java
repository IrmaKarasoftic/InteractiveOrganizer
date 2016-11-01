package com.example.amrairma.interactiveorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pocetniScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni_screen);
        Button button= (Button) findViewById(R.id.buttonOK);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(pocetniScreen.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}
