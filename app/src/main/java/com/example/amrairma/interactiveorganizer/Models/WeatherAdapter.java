package com.example.amrairma.interactiveorganizer.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amrairma.interactiveorganizer.R;

import java.util.List;


public class WeatherAdapter extends ArrayAdapter<ForecastDay> {

    private int resource;
    private Context context;
    private LinearLayout newView;
    public WeatherAdapter(Context context, int resource, List<ForecastDay> items) {
        super(context, resource, items);
        this.context=context;
        this.resource=resource;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            newView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li;
            li = (LayoutInflater) getContext().
                    getSystemService(inflater);
            li.inflate(resource, newView, true);
        } else {
            newView = (LinearLayout) convertView;
        }
        ForecastDay day = getItem(position);
        final TextView date = (TextView)newView.findViewById(R.id.date);
        final ImageView image = (ImageView)newView.findViewById(R.id.image);
        final TextView temperature =(TextView)newView.findViewById(R.id.avg_temp);
        final TextView minTemperature = (TextView)newView.findViewById(R.id.min_temp);
        final TextView maxTemperature = (TextView)newView.findViewById(R.id.max_temp);
        final TextView text = (TextView)newView.findViewById(R.id.text);

        date.setText(day.getDate());
        temperature.setText("Average temperature: "+String.valueOf(day.getDay().getAverageTemp()));
        minTemperature.setText("Min temperature "+String.valueOf(day.getDay().getMinTemp()));
        maxTemperature.setText("Max temperature "+String.valueOf(day.getDay().getMaxTemp()));
        String a = day.getDay().getCondition().getIcon();
        text.setText(day.getDay().getCondition().getText());

        String oznaka=day.getDay().getCondition().getText().toLowerCase();
        if ( oznaka.contains("snow")) image.setImageResource(R.drawable.snow);
        else if (oznaka.contains("sun")) image.setImageResource(R.drawable.sun);
        else if (oznaka.contains("rain")) image.setImageResource(R.drawable.rain);
        else if (oznaka.contains("mist")) image.setImageResource(R.drawable.mist);

        //Toast.makeText(context, a, Toast.LENGTH_LONG).show();

        /*Glide.with(context)
                .load(a)
                .placeholder(R.drawable.right_arrow)
                .into((ImageView)newView.findViewById(R.id.image)); */

        return newView;



    }


}
