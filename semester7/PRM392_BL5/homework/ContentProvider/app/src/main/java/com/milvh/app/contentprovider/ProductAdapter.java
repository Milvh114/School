package com.milvh.app.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.contentprovider.databinding.ProductItemBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Cursor cursor;

    public ProductAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout using ViewBinding
        ProductItemBinding binding = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            // Lấy dữ liệu từ cursor và gán vào view
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("ProductDescription"));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow("ProductPrice"));

            // Gán dữ liệu vào các view
            holder.binding.productId.setText(String.valueOf(id));
            holder.binding.productName.setText(name);
            holder.binding.productDescription.setText(description);
            holder.binding.productPrice.setText(String.valueOf(price));
        }
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ProductItemBinding binding;

        public ViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    // Thêm phương thức để thay đổi dữ liệu Cursor khi cần
    public void changeCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        notifyDataSetChanged();
    }

    // Getter for cursor
    public Cursor getCursor() {
        return cursor;
    }
}
