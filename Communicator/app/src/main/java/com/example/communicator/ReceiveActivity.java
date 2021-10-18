package com.example.communicator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        TextView tvMessage = findViewById(R.id.tvMessage);
        String messageText = getIntent().getExtras().getString(EXTRA_MESSAGE);
        tvMessage.setText(messageText);
    }
}