package com.example.foodplanner.welcome_screen;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.remote.auth.AuthService;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;


public class WelcomeFragment extends Fragment {

    Button googleBtn;
    Button emailBtn;

    TextView loginTv;


    public WelcomeFragment() {
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
        return inflater.inflate(R.layout.activity_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeUI(view);

        // sign up
        emailBtn.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_welcomeFragment_to_signupFragment);
        });
        // google auth
        googleBtn.setOnClickListener(v -> {

        });

        // normal log in
        loginTv.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_welcomeFragment_to_loginFragment);
        });

    }

    void initializeUI(View view){
        emailBtn = view.findViewById(R.id.btn_emailsignup);
        googleBtn = view.findViewById(R.id.btn_google);
        loginTv = view.findViewById(R.id.tv_login);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}