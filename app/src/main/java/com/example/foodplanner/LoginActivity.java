package com.example.foodplanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import auth.AuthCallback;
import auth.AuthService;
import auth.FirebaseAuthService;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    Button loginBtn;
    EditText emailEt;
    EditText passwordEt;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.btn_login);
        emailEt = findViewById(R.id.et_email1);
        passwordEt = findViewById(R.id.et_password1);

        authService = new FirebaseAuthService();

        loginBtn.setOnClickListener(v -> {
            userLogin(emailEt.getText().toString(), passwordEt.getText().toString());
        });
    }

    private void userLogin(String email, String password){
        authService.login(email, password, new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                Log.d(TAG, "signInWithEmail:success");
                Toast.makeText(LoginActivity.this, "Authentication succeeded.", Toast.LENGTH_SHORT).show();
                // Navigate to the next screen
            }

            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "signInWithEmail:failure", e);
                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}