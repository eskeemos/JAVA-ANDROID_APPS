package com.example.coffeehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = getIntent().getExtras().getInt(EXTRA_DRINK_ID);
        
        try {
            SQLiteOpenHelper opener = new CoffeehouseDB(this);
            SQLiteDatabase db = opener.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                new String[]{"NAME", "DESC", "IMG_RESOURCE", "FAVORITE"},
                "_id = ?",
                new String[]{Integer.toString(drinkId)},
                null, null, null, null
                );

            if(cursor.moveToLast()) {
                String name = cursor.getString(0);
                TextView tbName = findViewById(R.id.tvName);
                tbName.setText(name);

                String desc = cursor.getString(1);
                TextView tvDesc = findViewById(R.id.tvDesc);
                tvDesc.setText(desc);

                int img_resource = cursor.getInt(2);
                ImageView ivImage = findViewById(R.id.ivImage);
                ivImage.setImageResource(img_resource);
                ivImage.setContentDescription(name);

                boolean isFav = (cursor.getInt(3) == 1);
                CheckBox cbFav = findViewById(R.id.cbFav);
                cbFav.setChecked(isFav);
                cbFav.setOnClickListener(this::favClicked);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database ERROR!", Toast.LENGTH_SHORT).show();
        }
    }

    private void favClicked(View view) {
        int id = getIntent().getExtras().getInt(EXTRA_DRINK_ID);
        new UpdateFavorites().execute(id);
    }

    @SuppressLint("StaticFieldLeak")
    private class UpdateFavorites extends AsyncTask<Integer, Void, Boolean> {
        ContentValues values;

        @Override
        protected void onPreExecute() {
            CheckBox cbFav = findViewById(R.id.cbFav);
            values = new ContentValues();
            values.put("FAVORITE", cbFav.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int drinkId = integers[0];

            try {
                SQLiteOpenHelper opener = new CoffeehouseDB(DrinkActivity.this);
                SQLiteDatabase db = opener.getWritableDatabase();
                db.update("DRINK",
                    values,
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)}
                    );
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(!aBoolean) {
                Toast.makeText(DrinkActivity.this, "Database ERROR!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}