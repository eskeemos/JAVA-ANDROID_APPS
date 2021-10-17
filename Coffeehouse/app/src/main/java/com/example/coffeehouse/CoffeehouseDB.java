package com.example.coffeehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CoffeehouseDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "caffeine";
    private static final int DB_VERSION = 1;

    public CoffeehouseDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        getNewestDB(sqLiteDatabase, 0, DB_VERSION);
    }

    private void getNewestDB(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        if(oldV < 1) {
            sqLiteDatabase.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESC TEXT, IMG_RESOURCE INTEGER)");
            insertDrink(sqLiteDatabase, "Iced Coffee", "A coffee with ice, typically served with a dash of milk, cream or sweetener", R.drawable.cafe);
            insertDrink(sqLiteDatabase, "Latte", "A shot of espresso and steamed milk with just a touch of foam.", R.drawable.cafe);
            insertDrink(sqLiteDatabase, "Cappuccino", "Cappuccino is a latte made with more foam than steamed milk with a sprinkle of cocoa powder", R.drawable.cafe);
        }

//        if(oldV < 2) {
//            sqLiteDatabase.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
//        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        getNewestDB(sqLiteDatabase, i, i1);
    }

    private static void insertDrink(SQLiteDatabase sqLiteDatabase, String name, String desc, int img_resource) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("DESC", desc);
        contentValues.put("IMG_RESOURCE", img_resource);
        sqLiteDatabase.insert("DRINK", null, contentValues);
    }
}
