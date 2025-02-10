package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import auth.AuthService;
import auth.FirebaseAuthService;

public class WelcomeActivity extends AppCompatActivity {

    Button googleBtn;
    Button emailBtn;

    TextView loginTv;
    Intent intent;
    private AuthService authService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        emailBtn = findViewById(R.id.btn_emailsignup);
        googleBtn = findViewById(R.id.btn_google);
        loginTv = findViewById(R.id.tv_login);

        authService = new FirebaseAuthService();
        emailBtn.setOnClickListener(v -> {
            intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        googleBtn.setOnClickListener(v -> {

        });
        loginTv.setOnClickListener(v -> {
            intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}