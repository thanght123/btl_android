package com.example.androidqlnh.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidqlnh.R;
import com.example.androidqlnh.dao.SQLiteHelper;
import com.example.androidqlnh.adapter.FoodRowAdapter;
import com.example.androidqlnh.model.OrderedDish;

import java.util.ArrayList;
import java.util.List;

public class EditFoodFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    private Button btnConfirm, btnSearch;
    private EditText sobanEdt;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_order_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.danh_sach_mon);
        sqLiteHelper = new SQLiteHelper(getContext());
        sobanEdt = view.findViewById(R.id.so_ban);
        btnConfirm = view.findViewById(R.id.confirm_button);
        btnSearch = view.findViewById(R.id.search);
        btnSearch.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    private List<OrderedDish> getOrderedDishList() {
        List<OrderedDish> orderedDishes = new ArrayList<>();
        FoodRowAdapter adapter = (FoodRowAdapter) recyclerView.getAdapter();
        orderedDishes = adapter.getOrderedDishes();
        for (int i = 0; i < orderedDishes.size(); i++) {
            orderedDishes.get(i).setAmount(adapter.getAmountByPosition(i));
            orderedDishes.get(i).setTableNo(Integer.parseInt(sobanEdt.getText().toString()));
        }
        return orderedDishes;
    }


    private void btnConfirmListen() {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            FoodRowAdapter adapter = new FoodRowAdapter();
            int table = Integer.parseInt(this.sobanEdt.getText().toString());
            List<OrderedDish> orderedDishes = sqLiteHelper.getOrderedDishByTable(table);
            adapter.setOrderedDishes(orderedDishes);
            recyclerView.setAdapter(adapter);
            if(orderedDishes.size() == 0){
                Toast.makeText(getContext(),"Bàn Này Chưa Được Đặt Món",Toast.LENGTH_LONG).show();
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:{
                btnConfirmListen();
                break;
            }
            case R.id.confirm_button:{
                int table = Integer.parseInt(this.sobanEdt.getText().toString());
                List<OrderedDish> orderedDishes = sqLiteHelper.getOrderedDishByTable(table);
                FoodRowAdapter adapter = (FoodRowAdapter) recyclerView.getAdapter();
                for (int i = 0; i < orderedDishes.size(); i++) {
                    orderedDishes.get(i).setAmount(adapter.getAmountByPosition(i));
                    orderedDishes.get(i).setTableNo(Integer.parseInt(sobanEdt.getText().toString()));
                }
                for(OrderedDish orderedDish: orderedDishes){
                    sqLiteHelper.updateOrderedDish(orderedDish);
                }
                Toast.makeText(getContext(),"Cập Nhật Thành Công",Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
