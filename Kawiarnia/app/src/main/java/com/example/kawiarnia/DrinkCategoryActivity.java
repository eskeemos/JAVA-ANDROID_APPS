package com.example.kawiarnia;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView drinkList = getListView();
        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, Drink.drinks
        );
        drinkList.setAdapter(listAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINK_NO, (int) id);
        startActivity(intent);
    }
}