package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import auth.AuthCallback;
import auth.AuthService;
import auth.FirebaseAuthService;
import utils.InputValidator;


public class SignupFragment extends Fragment {

    private static final String TAG = "SignUpScreen";
    EditText usernameEt;
    EditText emailEt;
    EditText passwordEt;
    EditText confirmPassEt;

    Button sigUpBtn;
    private AuthService authService;

    private View view;

    public SignupFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authService = new FirebaseAuthService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        usernameEt = view.findViewById(R.id.et_username);
        emailEt = view.findViewById(R.id.et_email);
        passwordEt = view.findViewById(R.id.et_password);
        confirmPassEt = view.findViewById(R.id.et_confirmPass);
        sigUpBtn = view.findViewById(R.id.btn_signup);

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


    private void createAccount(String email, String password){
        authService.signUpWithEmail(email, password, new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                Log.d(TAG, "Sign-up successful");
                Toast.makeText(requireContext(), "Sign-up successful! Please login.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_homeFragment);
            }

            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "Sign-up failed", e);
                Toast.makeText(requireContext(), "Sign-up failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}