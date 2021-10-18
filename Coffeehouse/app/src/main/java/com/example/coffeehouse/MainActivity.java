package com.example.coffeehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CursorAdapter adapter;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener categoryClicked =
            (adapterView, view, i, l) -> {
                if(i == 0) {
                    Intent intent = new Intent(MainActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            };

        ListView categories = findViewById(R.id.lvCategories);
        categories.setOnItemClickListener(categoryClicked);

        ListView lvFavDrinks = findViewById(R.id.lvFavDrinks);

        try {
            SQLiteOpenHelper opener = new CoffeehouseDB(this);
            db = opener.getReadableDatabase();
             cursor = db.query("DRINK",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null, null, null, null, null
                );
            adapter = new SimpleCursorAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{"NAME"},
                new int[]{android.R.id.text1},
                0
            );

            lvFavDrinks.setAdapter(adapter);

        } catch (SQLiteException e) {
            Toast.makeText(this, "Database ERROR!", Toast.LENGTH_SHORT).show();
        }

        lvFavDrinks.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, DrinkActivity.class);
            intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, (int) l);
            startActivity(intent);
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            cursor = db.query("DRINK",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null, null, null, null, null);
            adapter.changeCursor(cursor);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database ERROR!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}