package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        setContentView(R.layout.activity_main);

        Button bStart = findViewById(R.id.bStart);
        bStart.setOnClickListener(this::StartTimer);

        Button bStop = findViewById(R.id.bStop);
        bStop.setOnClickListener(this::StopTimer);

        Button bRestart = findViewById(R.id.bRestart);
        bRestart.setOnClickListener(this::RestartTimer);

        runTimer();
    }

    private void RestartTimer(View view) {
        seconds = 0;
    }

    private void StopTimer(View view) {
        running = false;
    }

    private void StartTimer(View view) {
        running = true;
    }

    private void runTimer() {
        TextView tvTime = findViewById(R.id.tvTime);
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int h = seconds / 3600;
                int m = (seconds / 3600) / 60;
                int s = seconds % 60;

                @SuppressLint("DefaultLocale") String time = String.format("%d:%02d:%02d",h,m,s);

                tvTime.setText(time);

                if(running) seconds++;

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onPause() {
        super.onPause();

        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        running = wasRunning;
    }
}