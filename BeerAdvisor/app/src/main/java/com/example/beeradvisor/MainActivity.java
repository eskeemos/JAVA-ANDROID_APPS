package com.example.beeradvisor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bGetBrands = findViewById(R.id.bGetBrands);
        bGetBrands.setOnClickListener(this::getBrands);
    }

    public void getBrands(View view){
        Spinner sBeerType = findViewById(R.id.sBeerTypes);
        String beerType = String.valueOf(sBeerType.getSelectedItem());

        BeerBrands beerBrands = new BeerBrands();
        List<String> brands = beerBrands.getBrands(beerType);

        TextView tvBrands = findViewById(R.id.tvBeerBrands);

        StringBuilder sb = new StringBuilder();

        for (String brand : brands)
        {
            sb.append(brand).append("\n");
        }

        tvBrands.setText(sb);
    }
}