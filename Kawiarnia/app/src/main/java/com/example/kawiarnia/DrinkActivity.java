package com.example.kawiarnia;

import androidx.appcompat.app.AppCompatActivity;

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

    public static final String EXTRA_DRINK_NO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINK_NO);

        try{
            SQLiteOpenHelper dbOpen = new KawiarniaDbHelper(this);
            SQLiteDatabase db = dbOpen.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                new String[]{"NAME", "DESC","IMG_RESOURCE_ID","FAVORITE"},
                "_id = ?",
                new String[] {Integer.toString(drinkNo)},
                null,null,null
            );

            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String descText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                TextView name = findViewById(R.id.name);
                TextView desc = findViewById(R.id.desc);
                ImageView img = findViewById(R.id.image);
                CheckBox favorite = findViewById(R.id.favorite);

                name.setText(nameText);
                desc.setText(descText);
                img.setImageResource(photoId);
                img.setContentDescription(nameText);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        }catch(SQLiteException e){
            Toast toast = Toast.makeText(this, "Błąd bazy danych", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
        int drinkNo = (Integer)getIntent().getExtras().get(EXTRA_DRINK_NO);
        new UpdateDrinkTask().execute(drinkNo);
    }


    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            int drinkNo = integers[0];
            SQLiteOpenHelper dbOpen = new KawiarniaDbHelper(DrinkActivity.this);
            try {
                SQLiteDatabase db = dbOpen.getWritableDatabase();
                db.update("DRINK",
                    drinkValues,
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)}
                );
                db.close();
                return  true;
            }catch(SQLiteException e){
                return  false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(!aBoolean){
                Toast.makeText(DrinkActivity.this, "Błąd bazy danych", Toast.LENGTH_SHORT).show();
            }
        }
    }
}