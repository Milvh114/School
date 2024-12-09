package com.milvh.app.intentshomework;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.et_name);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(view -> {
            String name = etName.getText().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("USER_NAME", name);
                startActivity(intent);
                finish();
            } else {
                etName.setError("Please enter your name");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etName.setText("");
    }
}