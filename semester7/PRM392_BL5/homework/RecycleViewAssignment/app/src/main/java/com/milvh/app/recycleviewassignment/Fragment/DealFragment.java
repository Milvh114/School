package com.milvh.app.recycleviewassignment.Fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.milvh.app.recycleviewassignment.DBHelper;
import com.milvh.app.recycleviewassignment.Product;
import com.milvh.app.recycleviewassignment.Adapter.ProductAdapter;
import com.milvh.app.recycleviewassignment.databinding.FragmentDealBinding;

import java.util.ArrayList;

public class DealFragment extends Fragment {

//    private FragmentDealBinding binding;
//
//    private DBHelper dbHelper;
//    private ArrayList<Product> productData;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        binding = FragmentDealBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        dbHelper = new DBHelper(getContext());
//        insertProduct("Laptop", "Abc...", 20000000);
//        insertProduct("Smartphone", "Xyz...", 15000000);
//        insertProduct("Headphone", "Kzz...", 1000000);
//
//        productData = getAllProducts();
//        initCategory();
//        System.out.println(productData.size());
//
//        // Gắn sự kiện cho các nút
//        binding.btnInsert.setOnClickListener(v -> insertProduct("Laptop", "Abc...", 20000000));
//        binding.btnUpdate.setOnClickListener(v -> updateProduct(1, "Laptop Pro", "Updated Description", 25000000));
//        binding.btnDelete.setOnClickListener(v -> deleteProduct(50));
//
//        return root;
//    }
//
//    private void initCategory() {
//        if (productData != null && productData.size() > 0) {
//            binding.dealView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//            RecyclerView.Adapter adapter = new ProductAdapter(productData);
//            binding.dealView.setAdapter(adapter);
//        }
//    }
//
//
//    private void insertProduct(String name, String description, int price) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("ProductName", name);
//        values.put("ProductDescription", description);
//        values.put("ProductPrice", price);
//
//        // Chèn vào bảng product
//        db.insert("product", null, values);
//
//        db.close(); // Đóng cơ sở dữ liệu sau khi xong
//    }
//
//    public void updateProduct(int id, String name, String description, int price) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("ProductName", name);
//        values.put("ProductDescription", description);
//        values.put("ProductPrice", price);
//
//        db.update("product", values, "ID = ?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//
//    public void deleteProduct(int id) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("product", "ID = ?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//
//    public ArrayList<Product> getAllProducts() {
//        ArrayList<Product> productList = new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM product", null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
//                String name = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
//                String description = cursor.getString(cursor.getColumnIndexOrThrow("ProductDescription"));
//                int price = cursor.getInt(cursor.getColumnIndexOrThrow("ProductPrice"));
//                productList.add(new Product(id, name, description, price));
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return productList;
//    }


    /////////////Provider code //////////////////////

    private FragmentDealBinding binding;
    private static final String AUTHORITY = "com.milvh.app.goodsprovider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/product");

    private ArrayList<Product> productData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDealBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Gắn sự kiện cho các nút
        binding.btnInsert.setOnClickListener(v -> insertProduct("Laptop", "Abc...", 20000000));
        binding.btnUpdate.setOnClickListener(v -> updateProduct(1, "Laptop Pro", "Updated Description", 25000000));
        binding.btnDelete.setOnClickListener(v -> deleteProduct(76));

        // Hiển thị danh sách sản phẩm
        productData = getAllProducts();
        initCategory();

        return root;
    }

    private void initCategory() {
        if (productData != null && productData.size() > 0) {
            binding.dealView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            RecyclerView.Adapter adapter = new ProductAdapter(productData);
            binding.dealView.setAdapter(adapter);
        }
    }

    private void insertProduct(String name, String description, int price) {
        ContentValues values = new ContentValues();
        values.put("ProductName", name);
        values.put("ProductDescription", description);
        values.put("ProductPrice", price);

        // Sử dụng ContentResolver để chèn dữ liệu vào ContentProvider
        getActivity().getContentResolver().insert(CONTENT_URI, values);
    }

    public void updateProduct(int id, String name, String description, int price) {
        ContentValues values = new ContentValues();
        values.put("ProductName", name);
        values.put("ProductDescription", description);
        values.put("ProductPrice", price);

        // Sử dụng ContentResolver để cập nhật dữ liệu
        Uri productUri = Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
        getActivity().getContentResolver().update(productUri, values, null, null);
    }

    public void deleteProduct(int id) {
        // Xóa sản phẩm với ID cụ thể
        String selection = "ID = ?";
        String[] selectionArgs = { String.valueOf(id) };

        // Sử dụng ContentResolver để xóa sản phẩm
        Uri productUri = Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
        getActivity().getContentResolver().delete(productUri, selection, selectionArgs);
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query(CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("ProductDescription"));
                int price = cursor.getInt(cursor.getColumnIndexOrThrow("ProductPrice"));
                productList.add(new Product(id, name, description, price));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return productList;
    }
}