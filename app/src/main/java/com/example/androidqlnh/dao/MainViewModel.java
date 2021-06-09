package com.example.androidqlnh.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidqlnh.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    public MainViewModel(){
        dishesLiveData.setValue(new ArrayList<Dish>());
    }
    public MutableLiveData<List<Dish>> dishesLiveData = new MutableLiveData<List<Dish>>();
}
