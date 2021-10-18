package com.example.notificationer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bGetNotification = findViewById(R.id.bGetNotification);
        bGetNotification.setOnClickListener(this::getNotification);
    }

    private void getNotification(View view) {
        Intent intent = new Intent(this, NotificationService.class);
        intent.putExtra(NotificationService.EXTRA_MESSAGE, getResources().getString(R.string.extra_message));
        startService(intent);
    }
}