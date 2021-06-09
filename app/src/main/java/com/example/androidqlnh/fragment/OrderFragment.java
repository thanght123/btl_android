package com.example.androidqlnh.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidqlnh.R;
import com.example.androidqlnh.dao.SQLiteHelper;
import com.example.androidqlnh.adapter.FoodRowAdapter;
import com.example.androidqlnh.model.Dish;
import com.example.androidqlnh.model.OrderedDish;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    private Button btnConfirm;
    private EditText so_ban;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.danh_sach_mon);
        sqLiteHelper = new SQLiteHelper(getContext());
        so_ban = view.findViewById(R.id.so_ban);
        btnConfirm = view.findViewById(R.id.confirm_button);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnConfirmListen();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FoodRowAdapter adapter = new FoodRowAdapter();
        List<Dish> dishes = sqLiteHelper.getAllDish();
        adapter.setDishes(dishes);
        recyclerView.setAdapter(adapter);
    }

    private List<OrderedDish> getOrderedDishList() {
        List<OrderedDish> orderedDishes = new ArrayList<>();
        FoodRowAdapter adapter = (FoodRowAdapter) recyclerView.getAdapter();
        orderedDishes = adapter.getOrderedDishes();
        for (int i = 0; i < orderedDishes.size(); i++) {
            orderedDishes.get(i).setAmount(adapter.getAmountByPosition(i));
            orderedDishes.get(i).setTableNo(Integer.parseInt(so_ban.getText().toString()));
        }
        return orderedDishes;
    }

    public void btnConfirmListen() {
        List<OrderedDish> orderedDishes = this.getOrderedDishList();
        for (OrderedDish orderedDish : orderedDishes) {
            System.out.println(orderedDish.getDishName() + " " + orderedDish.getAmount());
        }
        this.sqLiteHelper.insertOrderedDishList(orderedDishes);
//        Toast.makeText(getContext(),"Đã Gọi Món Thành Công", Toast.LENGTH_LONG).show();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "order")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Gọi món thành công!")
                .setContentText("Đã gọi món cho bàn " + so_ban.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        int soban = Integer.parseInt(so_ban.getText().toString());
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
        managerCompat.notify(soban, builder.build());
    }
}
