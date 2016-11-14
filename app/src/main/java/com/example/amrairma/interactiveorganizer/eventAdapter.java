package com.example.amrairma.interactiveorganizer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by irmaka on 11/14/2016.
 */

public class eventAdapter extends ArrayAdapter {
    List<CalendarEvent> _events;
    Context context = null;

    public eventAdapter(Context context, int resource, List<CalendarEvent> events)
    {
        super(context,resource,events);
        this.context=context;
//        this._events=events;
    }
    public List<?> getCollection() {return _events;};
    @Override
    public int getCount() {
        return _events.size();
    }

    @Override
    public Object getItem(int position) {
        return _events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CalendarEvent events=_events.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.property_layout, null);

        TextView title= (TextView) view.findViewById(R.id.eventTitle);
        TextView description= (TextView) view.findViewById(R.id.eventDescription);
        TextView dateAndTime= (TextView) view.findViewById(R.id.eventTime);
        title.setText(String.valueOf(events.getTitle()));
        description.setText(String.valueOf(events.getDescription()));
        dateAndTime.setText(String.valueOf(events.getDateAndTime()));

        return view;
    }
}
