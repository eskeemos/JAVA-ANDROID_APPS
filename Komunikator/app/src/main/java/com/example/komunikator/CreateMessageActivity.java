package com.example.komunikator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }
    public void onMessageSend(View view)
    {
        EditText messageView = findViewById(R.id.etMessage);
        String messageText = messageView.getText().toString();

//        Intent intent = new Intent(this, ReceiveMessageActivity.class);
//        intent.putExtra("message", messageText);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        startActivity(intent);
    }

}