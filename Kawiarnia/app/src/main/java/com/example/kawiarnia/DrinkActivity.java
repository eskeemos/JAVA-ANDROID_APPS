package com.example.kawiarnia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_NO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINK_NO);
        Drink drink = Drink.drinks[drinkNo];

        ImageView image = findViewById(R.id.image);
        image.setImageResource(drink.getImageResourceId());
        image.setContentDescription(drink.getName());

        TextView name = findViewById(R.id.name);
        name.setText(drink.getName());

        TextView desc = findViewById(R.id.desc);
        desc.setText(drink.getDesc());

    }
}