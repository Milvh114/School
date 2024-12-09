package com.milvh.app.recycleviewassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.recycleviewassignment.Product;
import com.milvh.app.recycleviewassignment.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewholder> {
    private ArrayList<Product> productList;
    Context context;


    public ProductAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.viewholder holder, int position) {
        holder.nameTextView.setText(productList.get(position).getName());
        holder.descriptionTextView.setText(productList.get(position).getDescription());
        holder.priceTextView.setText(String.valueOf(productList.get(position).getPrice()));
        holder.idTextView.setText(String.valueOf(productList.get(position).getId()));
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView nameTextView, descriptionTextView, priceTextView, idTextView;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.productName);
            descriptionTextView = itemView.findViewById(R.id.productDescription);
            priceTextView = itemView.findViewById(R.id.productPrice);
            idTextView = itemView.findViewById(R.id.productId);
        }
    }
}
