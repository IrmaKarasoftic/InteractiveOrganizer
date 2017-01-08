package com.example.amrairma.interactiveorganizer;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Irma on 1/9/2017.
 */

public class PropertyFragment extends Fragment implements View.OnClickListener {
    Button myButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {
        View myView = inflater.inflate(R.layout.property_layout, container, false);
        myButton = (Button) myView.findViewById(R.id.emailSend);
        myButton.setOnClickListener(this);
        return myView;
    }
    @Override
    public void onClick(View v) {
                Intent intent = new Intent(getActivity(), emailActivity.class);
                startActivity(intent);
    }
}