package com.milvh.app.recycleviewassignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.recycleviewassignment.Category;
import com.milvh.app.recycleviewassignment.Activity.ProducDetailActivity;
import com.milvh.app.recycleviewassignment.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder>{


    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cat_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {

        holder.catNameTxt.setText(items.get(position).getName());
        holder.catImg.setImageResource(items.get(position).getImagePath());

        holder.itemView.setOnClickListener(v -> {
            // Create an Intent to start DetailActivity
            Intent intent = new Intent(context, ProducDetailActivity.class);

            // Pass data to DetailActivity
            intent.putExtra("categoryName", items.get(position).getName());
            intent.putExtra("categoryImage", items.get(position).getImagePath());
            intent.putExtra("categoryPhone", items.get(position).getPhone());
            // Start the new activity
            context.startActivity(intent);
        });

//        holder.catNameTxt.setOnClickListener(v -> {
//            // Create an Intent to start DetailActivity
//            Intent intent = new Intent(context, ProducDetailActivity.class);
//
//            // Pass data to DetailActivity
//            intent.putExtra("categoryName", items.get(position).getName());
//            intent.putExtra("categoryImage", items.get(position).getImagePath());
//
//            // Start the new activity
//            context.startActivity(intent);
//        });
//
//        holder.catImg.setOnClickListener(v -> {
//            // Create an Intent to start DetailActivity
//            Intent intent = new Intent(context, ProducDetailActivity.class);
//
//            // Pass data to DetailActivity
//            intent.putExtra("categoryName", items.get(position).getName());
//            intent.putExtra("categoryImage", items.get(position).getImagePath());
//
//            // Start the new activity
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        private ImageView catImg;
        private TextView catNameTxt;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            catNameTxt = itemView.findViewById(R.id.catNameTxt);
            catImg = itemView.findViewById(R.id.catImg);
        }
    }
}

