package com.example.amrairma.interactiveorganizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class emailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        final String eventTitle = getIntent().getStringExtra("eventTitle");
        final String eventTime = getIntent().getStringExtra("eventTime");
        final String eventDesc = getIntent().getStringExtra("eventDesc");
        CharSequence[] c = getIntent().getCharSequenceArrayExtra("eventTitle");

        Button send = (Button)findViewById(R.id.sendMail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                TextView mail1=(TextView) findViewById(R.id.mailTo1);
                TextView mail2=(TextView) findViewById(R.id.mailTo2);
                TextView mail3=(TextView) findViewById(R.id.mailTo3);


                String[] addresses = {
                  mail1.toString(),mail2.toString(),mail3.toString()
                };
                if(mail1.getText()!= "" || mail1.getText()!= null) emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, addresses);

                String message = "Event title: "+eventTitle + " ("+eventDesc+") will be on: "+eventTime;
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Event information");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);


                emailIntent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(emailIntent,
                            "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                }

            }
        });

    }
}
