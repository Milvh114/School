package com.milvh.app.recycleviewassignment.Activity;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.milvh.app.recycleviewassignment.R;
import com.milvh.app.recycleviewassignment.databinding.ActivityProducDetailBinding;

public class ProducDetailActivity extends AppCompatActivity {

    ActivityProducDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProducDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get references to views
        TextView catName = findViewById(R.id.textView);
        ImageView catImage = findViewById(R.id.imageView);
        TextView catPhone = findViewById(R.id.phoneTextView);

        // Get data from intent
        int categoryImageResId = getIntent().getIntExtra("categoryImage", -1);
        String categoryName = getIntent().getStringExtra("categoryName");
        String categoryPhone = getIntent().getStringExtra("categoryPhone");

        // Set data to views
        catName.setText(categoryName);
        catImage.setImageResource(categoryImageResId);
        catPhone.setText(categoryPhone);

        catPhone.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + categoryPhone));
                startActivity(callIntent);
            }
        });
    }
}