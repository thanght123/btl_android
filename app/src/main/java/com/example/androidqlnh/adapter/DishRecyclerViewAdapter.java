package com.example.androidqlnh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidqlnh.R;
import com.example.androidqlnh.model.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.DishHolder> {
    public List<Dish> dishes = new ArrayList<>();

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    //init data dishes
    public DishRecyclerViewAdapter(){
        Dish dish = new Dish();
        dish.setDishId(0);
        dish.setDishName("Cơm niêu");
        dish.setDishType(1);
        dish.setDishPrice(30000);
        dish.setRatings(4);

        Dish dish2 = new Dish();
        dish2.setDishId(1);
        dish2.setDishName("Bún Bò Huế");
        dish2.setDishType(2);
        dish2.setDishPrice(25000);
        dish2.setRatings(4);

        Dish dish3 = new Dish();
        dish3.setDishId(2);
        dish3.setDishName("Bún Bề Bề");
        dish3.setDishType(3);
        dish3.setDishPrice(30000);
        dish3.setRatings(5);

        Dish dish4 = new Dish();
        dish4.setDishId(3);
        dish4.setDishName("Bún Riêu Cua");
        dish4.setDishType(4);
        dish4.setDishPrice(25000);
        dish4.setRatings(4);

        Dish dish5 = new Dish();
        dish5.setDishId(4);
        dish5.setDishName("Bún Đậu Mắm Tôm");
        dish5.setDishType(5);
        dish5.setDishPrice(30000);
        dish5.setRatings(5);

        Dish dish6 = new Dish();
        dish6.setDishId(5);
        dish6.setDishName("Cơm Gà Kho");
        dish6.setDishType(6);
        dish6.setDishPrice(30000);
        dish6.setRatings(3);

        Dish dish7 = new Dish();
        dish7.setDishId(6);
        dish7.setDishName("Cơm Gà Xối Mắm");
        dish7.setDishType(7);
        dish7.setDishPrice(40000);
        dish7.setRatings(4);

        Dish dish8 = new Dish();
        dish8.setDishId(7);
        dish8.setDishName("Nem Nướng");
        dish8.setDishType(8);
        dish8.setDishPrice(30000);
        dish8.setRatings(4);

        Dish dish9 = new Dish();
        dish9.setDishId(8);
        dish9.setDishName("Phở Bò Tái");
        dish9.setDishType(9);
        dish9.setDishPrice(30000);
        dish9.setRatings(4);

        Dish dish10 = new Dish();
        dish10.setDishId(9);
        dish10.setDishName("Trứng Vịt Lộn");
        dish10.setDishType(10);
        dish10.setDishPrice(7000);
        dish10.setRatings(4);

        dishes.add(dish);
        dishes.add(dish2);
        dishes.add(dish3);
        dishes.add(dish4);
        dishes.add(dish5);
        dishes.add(dish6);
        dishes.add(dish7);
        dishes.add(dish8);
        dishes.add(dish9);
        dishes.add(dish10);
    }

    @NonNull
    @Override
    public DishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new DishHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishHolder holder, int position) {
        System.out.println(dishes.size());
        holder.ten.setText(dishes.get(position).getDishName());
        holder.gia.setText("" + dishes.get(position).getDishPrice() + " VNĐ");
        if (dishes.get(position).getDishType() == 1) {
            holder.imageView.setImageResource(R.drawable.com_nieu);
        } else if (dishes.get(position).getDishType() == 2) {
            holder.imageView.setImageResource(R.drawable.bun_bo_hue);
        } else if (dishes.get(position).getDishType() == 3) {
            holder.imageView.setImageResource(R.drawable.bun_be_be);
        } else if (dishes.get(position).getDishType() == 4) {
            holder.imageView.setImageResource(R.drawable.bun_rieu_cua);
        } else if (dishes.get(position).getDishType() == 5) {
            holder.imageView.setImageResource(R.drawable.bun_dau_mam_tom);
        } else if (dishes.get(position).getDishType() == 6) {
            holder.imageView.setImageResource(R.drawable.com_ga_kho);
        } else if (dishes.get(position).getDishType() == 7) {
            holder.imageView.setImageResource(R.drawable.com_ga_xoi_mam);
        } else if (dishes.get(position).getDishType() == 8) {
            holder.imageView.setImageResource(R.drawable.nem_nuong);
        } else if (dishes.get(position).getDishType() == 9) {
            holder.imageView.setImageResource(R.drawable.pho);
        } else if (dishes.get(position).getDishType() == 10) {
            holder.imageView.setImageResource(R.drawable.trung_vit_lon);
        }
        holder.rating.setRating(dishes.get(position).getRatings());
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    class DishHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ten;
        TextView gia;
        RatingBar rating;
        Button button;

        public DishHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            ten = itemView.findViewById(R.id.ten);
            gia = itemView.findViewById(R.id.gia);
            rating = itemView.findViewById(R.id.rating);
            button = itemView.findViewById(R.id.button);
        }
    }
}
