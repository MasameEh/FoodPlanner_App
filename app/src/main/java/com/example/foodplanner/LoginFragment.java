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
import model.User;
import utils.InputValidator;


public class LoginFragment extends Fragment {


    private static final String TAG = "LoginScreen";
    Button loginBtn;
    EditText emailEt;
    EditText passwordEt;
    private View view;

    private AuthService authService;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        loginBtn = view.findViewById(R.id.btn_login);
        emailEt = view.findViewById(R.id.et_email1);
        passwordEt = view.findViewById(R.id.et_password1);

        authService = new FirebaseAuthService();

        loginBtn.setOnClickListener(v -> {
                if (InputValidator.validateLoginInputs(emailEt, passwordEt)) {
                    userLogin(emailEt.getText().toString().trim(), passwordEt.getText().toString().trim());
                }

        });
    }

    private void userLogin(String email, String password){
        authService.login(email, password, new AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                Log.d(TAG, "signInWithEmail:success");
                //sToast.makeText(requireContext(), "Authentication succeeded.", Toast.LENGTH_SHORT).show();
                // Navigate to the next screen
                User user1 =  new User(email, email);
                LoginFragmentDirections.ActionLoginFragmentToHomeFragment action =
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(user1);
                Navigation.findNavController(view).navigate(action);

            }

            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "signInWithEmail:failure", e);
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}