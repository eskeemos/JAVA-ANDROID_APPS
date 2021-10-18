package com.example.odometer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private OdometerService service;
    private boolean bound = false;

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            OdometerService.OdometerBinder binder = (OdometerService.OdometerBinder) iBinder;
            service = binder.getOdometer();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, OdometerService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(bound) {
            unbindService(conn);
            bound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trackDistance();
    }

    private void trackDistance() {
        TextView tvDistance = findViewById(R.id.tvDistance);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if(service != null) {
                    distance += service.getDistance();
                }
                @SuppressLint("DefaultLocale") String distanceString = String.format("%1$,.2f", distance);
                tvDistance.setText(distanceString);
                handler.postDelayed(this, 1000);
            }
        });
    }
}