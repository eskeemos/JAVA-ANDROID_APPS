package com.example.stoper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean wasRunning = false;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);

    }

    public void onClickStart(View view)
    {
        running = true;
    }
    public void onClickStop(View view)
    {
        running = false;
    }
    public void onClickRestart(View view)
    {
        seconds = 0;
    }
    private void runTimer()
    {
        final TextView timeView = (TextView) findViewById(R.id.tvTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                @SuppressLint("DefaultLocale") String time = String.format("%d:%02d:%02d",hours, minutes, secs);
                timeView.setText(time);

                if(running)
                {
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }
}