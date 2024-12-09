package com.milvh.app.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.contentprovider.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private static final String CONTENT_URI = "content://com.milvh.app.goodsprovider/product"; // URI đúng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Chuyển đổi String URI thành Uri object
        Uri contentUri = Uri.parse(CONTENT_URI);

        // Truy vấn dữ liệu từ ContentProvider
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);

        // Kiểm tra nếu cursor không null
        if (cursor != null) {
            // Khởi tạo ProductAdapter với Cursor
            ProductAdapter adapter = new ProductAdapter(cursor);
            binding.dealView.setLayoutManager(new LinearLayoutManager(this));
            binding.dealView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đảm bảo đóng cursor khi Activity bị hủy
        if (binding.dealView.getAdapter() != null) {
            Cursor cursor = ((ProductAdapter) binding.dealView.getAdapter()).getCursor();
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}