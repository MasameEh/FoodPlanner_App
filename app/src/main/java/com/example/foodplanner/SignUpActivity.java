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

import utils.InputValidator;


public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    EditText usernameEt;
    EditText emailEt;
    EditText passwordEt;
    EditText confirmPassEt;

    Button sigUpBtn;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();

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
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

        }
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            String msg = task.getException().getMessage();
                            Toast.makeText(SignUpActivity.this,  msg,
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }


}