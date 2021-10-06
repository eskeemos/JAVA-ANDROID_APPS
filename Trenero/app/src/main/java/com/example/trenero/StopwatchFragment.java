package com.example.trenero;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import android.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchFragment extends Fragment implements View.OnClickListener {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    public StopwatchFragment() {
        // Required empty public constructor
    }

    public static StopwatchFragment newInstance() {
        StopwatchFragment fragment = new StopwatchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if(wasRunning)
            {
                running = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);

        Button bStart = (Button) layout.findViewById(R.id.bStart);
        bStart.setOnClickListener(this);
        Button bStop = (Button) layout.findViewById(R.id.bStop);
        bStop.setOnClickListener(this);
        Button bRestart = (Button) layout.findViewById(R.id.bRestart);
        bRestart.setOnClickListener(this);

        return layout;
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
    public void onResume() {
        super.onResume();
        running = wasRunning;
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

    public void runTimer(View view)
    {
        final TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int h = seconds / 3600;
                int m = (seconds % 3600) / 60;
                int s = seconds % 60;
                @SuppressLint("DefaultLocale") String time = String.format("%d:%02d:%02d", h, m, s);
                tvTime.setText(time);
                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bStart:
                onClickStart(view);
                break;
            case R.id.bStop:
                onClickStop(view);
                break;
            case R.id.bRestart:
                onClickRestart(view);
                break;
        }
    }
}