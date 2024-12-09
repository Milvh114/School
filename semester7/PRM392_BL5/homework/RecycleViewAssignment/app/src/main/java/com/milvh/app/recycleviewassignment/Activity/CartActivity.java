package com.milvh.app.recycleviewassignment.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.recycleviewassignment.Adapter.CartAdapter;
import com.milvh.app.recycleviewassignment.Category;
import com.milvh.app.recycleviewassignment.R;
import com.milvh.app.recycleviewassignment.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;

    private ArrayList<Category> cartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        Data();
        initProductCart();


    }

    private void initProductCart() {

        if (cartData.size() > 0) {
            binding.cartRcclView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
            RecyclerView.Adapter adapter = new CartAdapter(cartData);
            binding.cartRcclView.setAdapter(adapter);
        }
    }


    private void Data() {

        cartData = new ArrayList<>();
        cartData.add(new Category(R.drawable.cat1_xxhdpi, "pharmacy","123-456-7890"));
        cartData.add(new Category(R.drawable.cat2_xxhdpi, "registry","123-456-7890"));
        cartData.add(new Category(R.drawable.cat3_xxhdpi, "cartwhell","123-456-7890"));
        cartData.add(new Category(R.drawable.cat4_xxhdpi, "clothing","123-456-7890"));
        cartData.add(new Category(R.drawable.cat5_xxhdpi, "shoes","123-456-7890"));
        cartData.add(new Category(R.drawable.cat6_xxhdpi, "accessories","123-456-7890"));
        cartData.add(new Category(R.drawable.cat7_xxhdpi, "baby","123-456-7890"));
        cartData.add(new Category(R.drawable.cat8_xxhdpi, "home","123-456-7890"));
        cartData.add(new Category(R.drawable.cat9_xxhdpi, "patio & garden","123-456-7890"));
    }

    // Hàm giả lập trả về danh sách sản phẩm

}