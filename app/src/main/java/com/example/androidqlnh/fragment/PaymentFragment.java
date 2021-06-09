package com.example.androidqlnh.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.Iterator;
import java.util.List;

public class PaymentFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    private EditText tableEdt;
    private TextView sumText;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payment_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.danh_sach_payment);
        sqLiteHelper = new SQLiteHelper(getContext());
        tableEdt = view.findViewById(R.id.table_no);
        sumText = view.findViewById(R.id.sum_text);
        Button payBtn = view.findViewById(R.id.pay_button);
        payBtn.setOnClickListener(this);
        Button confirmBtn = view.findViewById(R.id.confirm_pay_button);
        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_button:{
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                FoodRowAdapter adapter = new FoodRowAdapter();
                int table = Integer.parseInt(this.tableEdt.getText().toString());
                List<OrderedDish> orderedDishes = sqLiteHelper.getOrderedDishByTable(table);
                long sum = 0;
                Iterator itr = orderedDishes.iterator();
                while (itr.hasNext()){
                    OrderedDish orderedDish = (OrderedDish) itr.next();
                    if(orderedDish.getAmount() == 0) itr.remove();
                    else{
                        sum += orderedDish.getDishPrice() * orderedDish.getAmount();
                    }
                }
                adapter.setOrderedDishes(orderedDishes);
                recyclerView.setAdapter(adapter);

                this.sumText.setText(String.valueOf(sum) + " đ");
                break;
            }
            case R.id.confirm_pay_button:{
                int table = Integer.parseInt(this.tableEdt.getText().toString());
                sqLiteHelper.deleteOrderedDish(table);
                Toast.makeText(getContext(),"Thanh Toán Thành Công",Toast.LENGTH_LONG).show();
                break;
            }
        }
    }


}
