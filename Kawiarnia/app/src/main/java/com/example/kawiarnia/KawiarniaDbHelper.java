package com.example.kawiarnia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KawiarniaDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "coffeina";
//    private static final int DB_VERSION = 1;
    private static final int DB_VERSION = 2;

    public KawiarniaDbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

        @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
            updateMyDatabase(sqLiteDatabase, i, i1);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(oldVersion < 1)
        {
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESC TEXT, IMG_RESOURCE_ID INTEGER)");
            insertDrink(db,"Latte", "Kawa Latte podawana z mleczną pianką", R.drawable.cafe);
            insertDrink(db,"Cappuccino", "Kawa Cappuccino podawana z mleczną pianką", R.drawable.cafe);
            insertDrink(db,"Espresso", "Kawa Espresso podawana z mleczną pianką", R.drawable.cafe);
        }
        if(oldVersion < 2)
        {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 3)
        {

        }
    }

    private static void insertDrink(SQLiteDatabase sqLiteDatabase, String name, String desc, int resourceId)
    {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESC", desc);
        drinkValues.put("IMG_RESOURCE_ID", resourceId);
        sqLiteDatabase.insert("DRINK", null, drinkValues);
    }



}
