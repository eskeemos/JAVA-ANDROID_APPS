package com.example.kawiarnia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favCursor;

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

        ListView listFavorites = findViewById(R.id.list_favorites);

        try {
            SQLiteOpenHelper dbOpen = new KawiarniaDbHelper(this);
            db = dbOpen.getReadableDatabase();
            favCursor = db.query("DRINK",
                new String[]{"_id","NAME"},
                "FAVORITE = 1",
                null,
                null, null, null, null
            );

            CursorAdapter cursorAdapter = new SimpleCursorAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                favCursor,
                new String[]{"NAME"},
                new int[]{android.R.id.text1},
                0
            );
            listFavorites.setAdapter(cursorAdapter);
        }catch (SQLiteException e){
            Toast.makeText(this, "Błąd bazy danych", Toast.LENGTH_SHORT).show();
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINK_NO, (int) l);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            KawiarniaDbHelper dbHelper = new KawiarniaDbHelper(this);
            db = dbHelper.getReadableDatabase();
            Cursor newCursor = db.query("DRINK",
                new String[]{"_id", "NAME"},
                "FAVORITE = 1",
                null,null,null,null
            );

            ListView listFavorites = findViewById(R.id.list_favorites);
            CursorAdapter cursorAdapter = (CursorAdapter) listFavorites.getAdapter();
            cursorAdapter.changeCursor(newCursor);
            favCursor = newCursor;
        }catch (SQLiteException e){
            Toast.makeText(this, "Błąd bazy danych", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favCursor.close();
        db.close();
    }
}