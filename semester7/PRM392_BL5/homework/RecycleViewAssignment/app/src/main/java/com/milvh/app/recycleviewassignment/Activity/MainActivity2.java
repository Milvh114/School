package com.milvh.app.recycleviewassignment.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.milvh.app.recycleviewassignment.Category;
import com.milvh.app.recycleviewassignment.R;
import com.milvh.app.recycleviewassignment.ViewPagerAdapter;
import com.milvh.app.recycleviewassignment.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding binding;
    private ArrayList<Category> catData;
    private ArrayList<Category> cartData;

    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hideSystemUI();
        initVariable();
        Data();
        setVariable();
        createNotificationChannel();
        checkCartAndShowNotification();
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    private void initVariable() {
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2 = binding.viewPager;
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout = binding.tabLayout;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }


    private void setVariable() {
        binding.cartView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, CartActivity.class);
            startActivity(intent);
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opt_search) {
            Toast.makeText(this, item.getTitle() + " onContextItemSelected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.opt_cart) {
            Toast.makeText(this, item.getTitle() + " onContextItemSelected", Toast.LENGTH_SHORT).show();
        }
//        else if (id == R.id.opt_logout) {
//            Toast.makeText(this, item.getTitle() + " onContextItemSelected", Toast.LENGTH_SHORT).show();
//        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.opt_search) {
            Toast.makeText(this, item.getTitle() + " onContextItemSelected", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.opt_cart) {
            Toast.makeText(this, item.getTitle() + " onContextItemSelected", Toast.LENGTH_SHORT).show();
        }
//        else if (id == R.id.opt_logout) {
//            Toast.makeText(this, item.getTitle() + " onContextItemSelected", Toast.LENGTH_SHORT).show();
//        }
        return super.onContextItemSelected(item);
    }


    /////////Notifi/////////
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Cart Notification Channel";
            String description = "Channel for Cart notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("cart_channel", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void checkCartAndShowNotification() {

        if (catData.size() > 0) {
            // Intent mở màn hình Cart khi nhấn vào thông báo
            Intent intent = new Intent(this, CartActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            // Tạo nội dung thông báo
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "cart_channel")
                    .setSmallIcon(R.drawable.baseline_shopping_cart_24)
                    .setContentTitle("Cart Reminder")
                    .setContentText("You have " + cartData.size() + " products in your cart!")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            // Hiển thị thông báo
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            notificationManager.notify(1, builder.build());
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


}