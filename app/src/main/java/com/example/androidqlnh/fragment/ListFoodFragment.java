package com.example.androidqlnh.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidqlnh.R;
import com.example.androidqlnh.dao.SQLiteHelper;
import com.example.androidqlnh.adapter.DishRecyclerViewAdapter;
import com.example.androidqlnh.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class ListFoodFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listfood_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        DishRecyclerViewAdapter adapter = new DishRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
        sqLiteHelper.resetTables();
        List<Dish> dishesInit = new ArrayList<>();
        dishesInit = adapter.dishes;
        for(Dish d: dishesInit){
            sqLiteHelper.insertDish(d);
        }
        List<Dish> dishes = sqLiteHelper.getAllDish();
        adapter.setDishes(dishes);
        adapter.notifyDataSetChanged();
    }
}
