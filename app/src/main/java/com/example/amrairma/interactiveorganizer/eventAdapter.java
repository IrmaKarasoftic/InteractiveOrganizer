package com.example.amrairma.interactiveorganizer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.amrairma.interactiveorganizer.RealmModels.RealmCalendarEvent;

import java.util.List;

/**
 * Created by irmaka on 11/14/2016.
 */

public class eventAdapter extends ArrayAdapter<RealmCalendarEvent> {
    private int resource;
    private Context context;
    private LinearLayout newView;
    public eventAdapter(Context context, int resource, List<RealmCalendarEvent> items) {
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
        RealmCalendarEvent day = getItem(position);
        final TextView eventTitle=(TextView)newView.findViewById(R.id.eventTitle);
        final TextView eventTime=(TextView)newView.findViewById(R.id.eventTime);
        final TextView eventDesc=(TextView)newView.findViewById(R.id.eventDescription);
        final TextView minT = (TextView) newView.findViewById(R.id.minT);
        final TextView maxT = (TextView) newView.findViewById(R.id.maxT);
        final ImageView image = (ImageView) newView.findViewById(R.id.weatherIcon);

        eventTime.setText(day.getTime());
        eventDesc.setText(day.getDescription());
        eventTitle.setText(day.getTitle());
        String type=day.getType();
        if(type.equals("Rodjendan"))
            eventTitle.setBackgroundColor(Color.parseColor("#4286f4"));
        else if(type.equals("Sastanak"))
            eventTitle.setBackgroundColor(Color.parseColor("#9b42f4"));
        else  eventTitle.setBackgroundColor(Color.parseColor("#f46542"));

        return newView;



    }
}
