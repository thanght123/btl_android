package com.example.androidqlnh.model;

import com.example.androidqlnh.model.Dish;

public class OrderedDish extends Dish {
    private int amount;
    private int tableNo;
    private boolean checked;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }
}
