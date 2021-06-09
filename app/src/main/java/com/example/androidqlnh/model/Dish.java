package com.example.androidqlnh.model;

public class Dish {
    protected int dishId;
    protected String dishName;
    protected int dishType;
    protected int dishPrice;
    protected int ratings;

    public Dish() {
    }

    public Dish(int dishId, String dishName, int dishType, int dishPrice, int ratings) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishType = dishType;
        this.dishPrice = dishPrice;
        this.ratings = ratings;
    }

    public int getDishType() {
        return dishType;
    }

    public void setDishType(int dishType) {
        this.dishType = dishType;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
