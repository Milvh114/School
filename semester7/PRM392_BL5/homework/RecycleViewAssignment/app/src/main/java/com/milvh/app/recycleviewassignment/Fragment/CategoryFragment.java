package com.milvh.app.recycleviewassignment.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.milvh.app.recycleviewassignment.Category;
import com.milvh.app.recycleviewassignment.Adapter.CategoryAdapter;
import com.milvh.app.recycleviewassignment.R;
import com.milvh.app.recycleviewassignment.databinding.FragmentCategoryBinding;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {


    private FragmentCategoryBinding binding;
    private ArrayList<Category> catData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Data();
        initCategory();
        return root;    }

    private void initCategory() {
        if (catData != null && catData.size() > 0) {
            binding.categoryView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            RecyclerView.Adapter adapter = new CategoryAdapter(catData);
            binding.categoryView.setAdapter(adapter);
        }
    }

    private void Data() {
        catData = new ArrayList<>();
        catData.add(new Category(R.drawable.cat1_xxhdpi, "pharmacy","123-456-7890"));
        catData.add(new Category(R.drawable.cat2_xxhdpi, "registry","123-456-7890"));
        catData.add(new Category(R.drawable.cat3_xxhdpi, "cartwhell","123-456-7890"));
        catData.add(new Category(R.drawable.cat4_xxhdpi, "clothing","123-456-7890"));
        catData.add(new Category(R.drawable.cat5_xxhdpi, "shoes","123-456-7890"));
        catData.add(new Category(R.drawable.cat6_xxhdpi, "accessories","123-456-7890"));
        catData.add(new Category(R.drawable.cat7_xxhdpi, "baby","123-456-7890"));
        catData.add(new Category(R.drawable.cat8_xxhdpi, "home","123-456-7890"));
        catData.add(new Category(R.drawable.cat9_xxhdpi, "patio & garden","123-456-7890"));
    }
}