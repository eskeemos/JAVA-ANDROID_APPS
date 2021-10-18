package com.example.communicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.etMessage);

        Button bSendMessage = findViewById(R.id.bSendMessage);
        bSendMessage.setOnClickListener(this::SendMessage);
    }

    private void SendMessage(View view) {
        Intent intent;
        switch (1) {
            case 1:
                intent = new Intent(this, ReceiveActivity.class);
                intent.putExtra(ReceiveActivity.EXTRA_MESSAGE, message.getText().toString());
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                String title = "Message sending...";
                Intent chooser = Intent.createChooser(intent, title);
                startActivity(chooser);
                break;
        }
    }
}