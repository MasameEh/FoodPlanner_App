package com.example.foodplanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import auth.AuthCallback;
import auth.AuthService;
import auth.FirebaseAuthService;
import utils.InputValidator;


public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    EditText usernameEt;
    EditText emailEt;
    EditText passwordEt;
    EditText confirmPassEt;

    Button sigUpBtn;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        usernameEt = findViewById(R.id.et_username);
        emailEt = findViewById(R.id.et_email);
        passwordEt = findViewById(R.id.et_password);
        confirmPassEt = findViewById(R.id.et_confirmPass);
        sigUpBtn = findViewById(R.id.btn_signup);
        // Initialize Firebase Auth
        authService = new FirebaseAuthService();

        sigUpBtn.setOnClickListener(v -> {
            String username  = usernameEt.getText().toString();
            String email  = emailEt.getText().toString();
            String password  = passwordEt.getText().toString();
            String passwordC  = confirmPassEt.getText().toString();
            if (InputValidator.validateSignupInputs(usernameEt, emailEt, passwordEt, confirmPassEt)) {
                createAccount(emailEt.getText().toString().trim(), passwordEt.getText().toString().trim());
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = authService.getCurrentUser();

        if(currentUser != null){

        }
    }

    private void createAccount(String email, String password){
        authService.signUpWithEmail(email, password, new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                Log.d(TAG, "Sign-up successful");
                Toast.makeText(SignUpActivity.this, "Sign-up successful! Please login.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "Sign-up failed", e);
                Toast.makeText(SignUpActivity.this, "Sign-up failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}