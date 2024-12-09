package com.milvh.app.recycleviewassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.recycleviewassignment.Category;
import com.milvh.app.recycleviewassignment.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {

    ArrayList<Category> items;
    Context context;

    public CartAdapter(ArrayList<Category> cartData) {
        this.items = cartData;
    }


    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.product_cart_item, parent, false);
        return new CartAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        holder.productNameView.setText(items.get(position).getName());
        holder.productImageView.setImageResource(items.get(position).getImagePath());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView productNameView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameView = itemView.findViewById(R.id.productNameView);
        }
    }
}
