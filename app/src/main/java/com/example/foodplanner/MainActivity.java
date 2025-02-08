package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button googleBtn;
    Button emailBtn;

    TextView loginTv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        emailBtn = findViewById(R.id.btn_emailsignup);
        googleBtn = findViewById(R.id.btn_google);
        loginTv = findViewById(R.id.tv_login);

        emailBtn.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        loginTv.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}