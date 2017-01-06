package com.example.amrairma.interactiveorganizer;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;


public class AlarmReciever extends BroadcastReceiver {


    private AudioManager myAudioManager;

    @Override
    public void onReceive(Context c, Intent intent) {


        myAudioManager = (AudioManager)c.getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Toast.makeText(c,"Now in silent Mode",
                Toast.LENGTH_LONG).show();
    }
}
