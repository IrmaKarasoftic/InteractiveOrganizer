package com.example.amrairma.interactiveorganizer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by irmaka on 11/14/2016.
 */

public class eventAdapter extends BaseAdapter {
    List<CalendarEvent> events=null;
    Context context = null;

    eventAdapter(List<CalendarEvent> events, Context context)
    {
        this.events=events;
        this.context=context;
    }
    public List<?> getCollection() {return events;};
    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_events, parent, false);
        }

        return convertView;
    }
}
