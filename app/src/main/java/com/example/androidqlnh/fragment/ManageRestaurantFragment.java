package com.example.androidqlnh.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidqlnh.R;

public class ManageRestaurantFragment extends Fragment implements View.OnClickListener {
    private Button btnOrder, btnEdit, btnPayment;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnOrder = view.findViewById(R.id.goi_mon);
        btnEdit = view.findViewById(R.id.sua_mon);
        btnPayment = view.findViewById(R.id.thanh_toan);
        btnOrder.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnPayment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.goi_mon:{
                fragment = new OrderFragment();
                replaceFragment(fragment);
                break;
            }
            case R.id.sua_mon:{
                fragment = new EditFoodFragment();
                replaceFragment(fragment);
                break;
            }
            case R.id.thanh_toan:{
                fragment = new PaymentFragment();
                replaceFragment(fragment);
                break;
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
