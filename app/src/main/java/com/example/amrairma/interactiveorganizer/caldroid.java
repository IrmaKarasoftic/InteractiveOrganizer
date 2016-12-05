package com.example.amrairma.interactiveorganizer;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.roomorama.caldroid.CalendarHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hirondelle.date4j.DateTime;

public class caldroid extends FragmentActivity {

    CaldroidFragment caldroidFragment = new CaldroidFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caldroid);
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();
    }


    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {
            SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");

            Toast.makeText(getApplicationContext(), formatter.format(date),
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onChangeMonth(int month, int year) {
            String text = "month: " + month + " year: " + year;
            Toast.makeText(getApplicationContext(), text,
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClickDate(Date date, View view) {
            SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");

            Toast.makeText(getApplicationContext(),
                    "Long click " + formatter.format(date),
                    Toast.LENGTH_SHORT).show();Calendar cal = Calendar.getInstance();
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("beginTime", System.currentTimeMillis());
            intent.putExtra("endTime", System.currentTimeMillis()+60000);
            intent.putExtra("title", "hiii");
            startActivity(intent);
            caldroidFragment.refreshView();
        }

        @Override
        public void onCaldroidViewCreated() {
            if (caldroidFragment.getLeftArrowButton() != null) {
                Toast.makeText(getApplicationContext(),
                        "Caldroid view is created", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    };

}
