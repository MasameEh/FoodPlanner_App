package com.example.foodplanner;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.auth.AuthService;
import com.example.foodplanner.auth.FirebaseAuthRepository;


public class WelcomeFragment extends Fragment {

    Button googleBtn;
    Button emailBtn;

    TextView loginTv;

    private AuthService authService;

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authService = new FirebaseAuthRepository(requireContext());
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
        emailBtn = view.findViewById(R.id.btn_emailsignup);
        googleBtn = view.findViewById(R.id.btn_google);
        loginTv = view.findViewById(R.id.tv_login);

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