package com.example.androidqlnh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.example.androidqlnh.R;
import com.example.androidqlnh.adapter.FragmentAdapter;
import com.example.androidqlnh.dao.SQLiteHelper;
import com.example.androidqlnh.model.Dish;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout mTabLayout = findViewById(R.id.tablayout);
        ViewPager mViewPager = findViewById(R.id.viewpager);
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, FragmentAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

//        init();
        initNotificationChannel();
    }

    private void initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    "order",
                    "Order Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel1.setDescription("Channel for ordering");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

    private void init() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

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

        sqLiteHelper.resetTables();
        sqLiteHelper.insertDish(dish);
        sqLiteHelper.insertDish(dish2);
        sqLiteHelper.insertDish(dish3);
        sqLiteHelper.insertDish(dish4);
        sqLiteHelper.insertDish(dish5);
        sqLiteHelper.insertDish(dish6);
        sqLiteHelper.insertDish(dish7);
        sqLiteHelper.insertDish(dish8);
        sqLiteHelper.insertDish(dish9);
        sqLiteHelper.insertDish(dish10);
    }
}