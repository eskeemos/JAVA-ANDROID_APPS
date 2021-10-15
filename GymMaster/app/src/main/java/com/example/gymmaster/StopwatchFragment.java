package com.example.gymmaster;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class StopwatchFragment extends Fragment {

    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning = false;

    public StopwatchFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        runTimer(view);

        Button bStart = view.findViewById(R.id.bStart);
        bStart.setOnClickListener(this::StartTimer);

        Button bStop = view.findViewById(R.id.bStop);
        bStop.setOnClickListener(this::StopTimer);

        Button bRestart = view.findViewById(R.id.bRestart);
        bRestart.setOnClickListener(this::RestartTimer);

        return view;
    }

    private void runTimer(View view) {
        TextView tvTime = view.findViewById(R.id.tvTime);
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

    private void RestartTimer(View view) {
        seconds = 0;
    }

    private void StopTimer(View view) {
        running = false;
    }

    private void StartTimer(View view) {
        running = true;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onPause() {
        super.onPause();

        wasRunning = running;
        running = false;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();

        running = wasRunning;
    }
}