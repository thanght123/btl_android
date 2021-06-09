package com.example.androidqlnh.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.androidqlnh.model.Dish;
import com.example.androidqlnh.model.OrderedDish;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "leducthang";
    private static final int VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDish = "CREATE TABLE dish(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "type INTEGER," +
                "price REAL," +
                "rating INTEGER)";
        db.execSQL(createDish);

        Log.d("DUC THANG","DONE!");
        String createDishes = "CREATE TABLE ordered(" +
                "idOrder INTEGER PRIMARY KEY AUTOINCREMENT," +
                "iddish INTEGER," +
                "amount INTEGER," +
                "tableno INTEGER," +
                "FOREIGN KEY(iddish) REFERENCES dish(id))";
        db.execSQL(createDishes);
    }

    public void resetTables(){
        String dropDish = "DROP TABLE IF EXISTS dish";
        String dropOrdered = "DROP TABLE IF EXISTS ordered";
        getWritableDatabase().execSQL(dropDish);
        getWritableDatabase().execSQL(dropOrdered);
        String createDish = "CREATE TABLE dish(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "type INTEGER," +
                "price REAL," +
                "rating INTEGER)";
        getWritableDatabase().execSQL(createDish);

        Log.d("DUC THANG","DONE!");
        String createDishes = "CREATE TABLE ordered(" +
                "idOrder INTEGER PRIMARY KEY AUTOINCREMENT," +
                "iddish INTEGER," +
                "amount INTEGER," +
                "tableno INTEGER," +
                "FOREIGN KEY(iddish) REFERENCES dish(id))";
        getWritableDatabase().execSQL(createDishes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertDish(Dish dish) {
        ContentValues values = new ContentValues();
        values.put("id", dish.getDishId());
        values.put("name", dish.getDishName());
        values.put("type", dish.getDishType());
        values.put("price",dish.getDishPrice());
        values.put("rating",dish.getRatings());
        getWritableDatabase().insert("dish", null, values);
    }
    public List<Dish> getAllDish() {
        List<Dish> dishes = new ArrayList<>();
        Cursor cursor = getReadableDatabase()
                .query("dish", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            Dish dish = new Dish();
            dish.setDishId(cursor.getInt(cursor.getColumnIndex("id")));
            dish.setDishName(cursor.getString(cursor.getColumnIndex("name")));
            dish.setDishType((cursor.getInt(cursor.getColumnIndex("type"))));
            dish.setDishPrice(cursor.getInt(cursor.getColumnIndex("price")));
            dish.setRatings(cursor.getInt(cursor.getColumnIndex("rating")));
            dishes.add(dish);
        }
        if (cursor != null) cursor.close();
        return dishes;
    }

    public Dish getDishById(int id) {
        String whereClause = "id = ?";
        String[] args = {Integer.toString(id)};
        Cursor cursor = getReadableDatabase()
                .query("dish", null, whereClause, args, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            Dish dish = new Dish();
            dish.setDishId(cursor.getInt(cursor.getColumnIndex("id")));
            dish.setDishName(cursor.getString(cursor.getColumnIndex("name")));
            dish.setDishType(cursor.getInt(cursor.getColumnIndex("type")));
            dish.setDishPrice(cursor.getInt(cursor.getColumnIndex("price")));
            dish.setRatings(cursor.getInt(cursor.getColumnIndex("rating")));
            cursor.close();
            return dish;
        }
        return null;
    }

    public int updateDish(Dish dish){
        String whereClause = "id = ?";
        String[] args = {Integer.toString(dish.getDishId())};
        ContentValues values = new ContentValues();
        values.put("id", dish.getDishId());
        values.put("name", dish.getDishName());
        values.put("type", dish.getDishType());
        values.put("price", dish.getDishPrice());
        values.put("rating", dish.getRatings());
        return getWritableDatabase().update("dish", values, whereClause, args);
    }

    public int deleteDish(int id){
        String whereClause = "id = ?";
        String[] args = {Integer.toString(id)};
        return getWritableDatabase().delete("dish", whereClause, args);
    }

    public long insertOrderedDish(OrderedDish orderedDish) {
        ContentValues values = new ContentValues();
        values.put("iddish", orderedDish.getDishId());
        values.put("amount", orderedDish.getAmount());
        values.put("tableno",orderedDish.getTableNo());
        return getWritableDatabase().insert("ordered", null, values);
    }

    public void insertOrderedDishList(List<OrderedDish> orderedDishes){
        for(OrderedDish orderedDish: orderedDishes){
            long a = insertOrderedDish(orderedDish);
        }
    }

    public List<OrderedDish> getOrderedDishByTable(int tableNo) {
        String whereClause = "ordered.tableno = ? and dish.id = ordered.iddish";
        String[] args = {Integer.toString(tableNo)};
        List<OrderedDish> orderedDishes = new ArrayList<>();
        Cursor cursor = getReadableDatabase()
                .query("ordered, dish", null, whereClause, args, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            OrderedDish orderedDish = new OrderedDish();
            orderedDish.setDishId(cursor.getInt(cursor.getColumnIndex("iddish")));
            orderedDish.setDishName(cursor.getString(cursor.getColumnIndex("name")));
            orderedDish.setDishType(cursor.getInt(cursor.getColumnIndex("type")));
            orderedDish.setDishPrice(cursor.getInt(cursor.getColumnIndex("price")));
            orderedDish.setRatings(cursor.getInt(cursor.getColumnIndex("rating")));
            orderedDish.setAmount(cursor.getInt(cursor.getColumnIndex("amount")));
            orderedDish.setTableNo(cursor.getInt(cursor.getColumnIndex("tableno")));
            if(orderedDish.getAmount() > 0){
                orderedDish.setChecked(true);
            }
            else {
                orderedDish.setChecked(false);
            }
            orderedDishes.add(orderedDish);
        }
        if (cursor != null) cursor.close();
        return orderedDishes;
    }

    public int updateOrderedDish(OrderedDish orderedDish){
        String whereClause = "iddish = ? and tableno = ?";
        String[] args = {Integer.toString(orderedDish.getDishId()) ,Integer.toString(orderedDish.getTableNo())};
        ContentValues values = new ContentValues();
        values.put("amount",orderedDish.getAmount());
        return getWritableDatabase().update("ordered", values, whereClause, args);
    }

    public int deleteOrderedDish(int tableNo){
        String whereClause = "tableno = ?";
        String[] args = {Integer.toString(tableNo)};
        return getWritableDatabase().delete("ordered", whereClause, args);
    }


}
