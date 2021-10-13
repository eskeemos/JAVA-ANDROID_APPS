package com.example.restauracja;

public class Pizza {
    private String name;
    private int imageId;

    public static final Pizza[] pizzas = {
        new Pizza("Roma", R.drawable.pizza),
        new Pizza("Verona", R.drawable.pizza),
        new Pizza("Torino", R.drawable.pizza)
    };

    public Pizza(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
