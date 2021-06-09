package com.example.androidqlnh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidqlnh.R;
import com.example.androidqlnh.model.Dish;
import com.example.androidqlnh.model.OrderedDish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoodRowAdapter extends RecyclerView.Adapter<FoodRowAdapter.FoodRowViewHolder> {
    private List<OrderedDish> orderedDishes = new ArrayList<>();
    private HashMap<Integer, FoodRowViewHolder> holders = new HashMap<>();

    public void setOrderedDishes(List<OrderedDish> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public void setDishes(List<Dish> dishes) {
        orderedDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            OrderedDish orderedDish = new OrderedDish();
            orderedDish.setDishId(dish.getDishId());
            orderedDish.setDishName(dish.getDishName());
            orderedDish.setDishType(dish.getDishType());
            orderedDish.setDishPrice(dish.getDishPrice());
            orderedDish.setRatings(dish.getRatings());
            orderedDishes.add(orderedDish);
        }
    }

    public List<OrderedDish> getOrderedDishes() {
        return orderedDishes;
    }

    public int getAmountByPosition(int position){
        if(this.holders.get(position) == null) return 0;
        return (this.holders.get(position).getAmount());
    }

    @NonNull
    @Override
    public FoodRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_row, parent, false);
        return new FoodRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRowViewHolder holder, int position) {
        OrderedDish orderedDish = orderedDishes.get(position);
        holder.checkBox.setChecked(orderedDish.isChecked());
        holder.nameTv.setText(orderedDish.getDishName());
        holder.priceTv.setText("" + orderedDish.getDishPrice() + "đ");
        if (orderedDish.getAmount() != 0) {
            holder.amountEt.setText("" + orderedDish.getAmount());
        }
        this.holders.put(position, holder);
    }

    @Override
    public int getItemCount() {
        return orderedDishes.size();
    }

    public class FoodRowViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView nameTv;
        TextView priceTv;
        EditText amountEt;

        public FoodRowViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            nameTv = itemView.findViewById(R.id.name_tv);
            priceTv = itemView.findViewById(R.id.price_tv);
            amountEt = itemView.findViewById(R.id.amount_et);
        }
        public int getAmount(){
            if(amountEt.getText().toString().equals("")) return 0;
            return Integer.parseInt(amountEt.getText().toString());
        }
    }
}
