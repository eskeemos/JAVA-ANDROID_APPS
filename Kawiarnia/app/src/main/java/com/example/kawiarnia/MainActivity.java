package com.example.kawiarnia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdapterView.OnItemClickListener itemClickListener =
                (adapterView, view, i, l) -> {
                    if(i == 0)
                    {
                        Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                        startActivity(intent);
                    }
                };
        ListView listView = findViewById(R.id.lvCategories);
        listView.setOnItemClickListener(itemClickListener);
    }
}