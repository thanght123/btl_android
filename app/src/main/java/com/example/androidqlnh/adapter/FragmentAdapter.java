package com.example.androidqlnh.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.androidqlnh.fragment.ListFoodFragment;
import com.example.androidqlnh.fragment.ManageRestaurantFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private  int num_pages = 2;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return new ListFoodFragment();
            }
            case 1:{
                return new ManageRestaurantFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return num_pages;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:{
                return "Danh Sách Món Ăn";
            }
            case 1:{
                return "Quản lý nhà hàng";
            }
        }
        return null;
    }
}
