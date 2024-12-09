package com.milvh.app.datastorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.milvh.app.datastorage.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        // Kiểm tra trạng thái đăng nhập
        checkLoginStatus();
        setContentView(binding.getRoot());
        setVariable();

    }


    private void checkLoginStatus() {
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Nếu đã đăng nhập, chuyển đến HomeActivity
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
//            return; // Dừng xử lý LoginActivity
        }

    }

    private void setVariable() {

        binding.loginButton.setOnClickListener( v -> {
            String userName = binding.usernameEditText.getText().toString();
            String password = binding.passwordEditText.getText().toString();

            if (userName.equals("admin") && password.equals("123456")) { // Kiểm tra tài khoản mẫu
                // Lưu trạng thái đăng nhập
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                // Chuyển đến HomeActivity
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
//                finish(); // Kết thúc LoginActivity
            } else {
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}