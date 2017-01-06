package com.example.amrairma.interactiveorganizer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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

        eventTime.setText(day.getTime());
        eventDesc.setText(day.getDescription());
        eventTitle.setText(day.getTitle());

        return newView;



    }
}
