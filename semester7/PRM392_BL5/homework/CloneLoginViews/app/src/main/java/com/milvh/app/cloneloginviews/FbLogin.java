package com.milvh.app.cloneloginviews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.milvh.app.cloneloginviews.databinding.ActivityFbLoginBinding;

public class FbLogin extends AppCompatActivity {

    ActivityFbLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityFbLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(v -> {
            String email = binding.userEdit.getText().toString();
            String pass = binding.passEdit.getText().toString();
            System.out.println( "Email: " + email + ", Password: " + pass);
            if(!email.isEmpty() && !pass.isEmpty()){

                if(email.equals("admin") && pass.equals("123456")){
                    Toast.makeText(FbLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FbLogin.this, "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(FbLogin.this, "please fill username and password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}