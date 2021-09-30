package com.example.kawiarnia;

import androidx.annotation.NonNull;

public class Drink {
    private final String name;
    private final String desc;
    private final int imageResourceId;

    public static final Drink[] drinks = {
            new Drink("Latte", "Kawa Latte podawana z mleczną pianką", R.drawable.cafe),
            new Drink("Cappuccino", "Kawa Cappuccino podawana z mleczną pianką", R.drawable.cafe),
            new Drink("Espresso", "Kawa Espresso podawana z mleczną pianką", R.drawable.cafe)
    };
    private Drink(String name, String desc, int imageResourceId)
    {
        this.name = name;
        this.desc = desc;
        this.imageResourceId = imageResourceId;
    }
    public String getDesc()
    {
        return  desc;
    }
    public String getName()
    {
        return  name;
    }
    public int getImageResourceId()
    {
        return  imageResourceId;
    }
    @NonNull
    public String toString()
    {
        return  this.name;
    }

}
