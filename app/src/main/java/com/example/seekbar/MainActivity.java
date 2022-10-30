package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    Switch sw;
    ImageView iv;
    SeekBar sb;
    MediaPlayer media;
    AudioManager audio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = findViewById(R.id.swMain);
        sw.setOnCheckedChangeListener(this);
        iv = (ImageView) findViewById(R.id.mouseMain);
        sb = (SeekBar) findViewById(R.id.sbMain);
        sb.setProgress(100);
        media= MediaPlayer.create(this,R.raw.we_are_the_champions);
        media.start();
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(max);
        sb.setProgress(max/2);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC,max/2,0);
        sb.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, i,0);
        float alpha = (float) i/100;
        iv.setAlpha(alpha);
    }
    public void onStartTrackingTouch(SeekBar SB)
    {
    }
    @Override
    public void onStopTrackingTouch(SeekBar SB)
    {
    }
    @Override
    public void onCheckedChanged(CompoundButton ButtonView, boolean isChecked) {
        if(isChecked)
            iv.setVisibility(View.VISIBLE);
        else iv.setVisibility(View.INVISIBLE);
    }

}