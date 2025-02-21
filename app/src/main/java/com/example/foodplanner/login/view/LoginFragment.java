package com.example.foodplanner.login.view;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.repo.AuthRepositoryImp;
import com.example.foodplanner.login.presenter.LoginPresenter;
import com.example.foodplanner.login.presenter.LoginPresenterImp;
import com.google.firebase.auth.FirebaseUser;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.remote.auth.AuthService;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;
import com.example.foodplanner.data.model.User;
import com.example.foodplanner.utils.InputValidator;


public class LoginFragment extends Fragment implements LoginView{

    private static final String TAG = "LoginScreen";
    Button loginBtn;
    EditText emailEt;
    EditText passwordEt;

    TextView signupTv;


    LoginPresenter loginPresenter;
    NavController navController;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenterImp(AuthRepositoryImp.getInstance(FirebaseRemoteDataSource.getInstance(),
                CacheHelper.getInstance(requireContext())), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeUI(view);

        navController = findNavController(view);
        signupTv.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_signupFragment);
        });

        loginBtn.setOnClickListener(v -> {
                if (InputValidator.validateLoginInputs(emailEt, passwordEt)) {
                    loginPresenter.loginUser(emailEt.getText().toString().trim(), passwordEt.getText().toString().trim());
                }

        });
    }

    private void initializeUI(View view){
        loginBtn = view.findViewById(R.id.btn_login);
        emailEt = view.findViewById(R.id.et_email1);
        passwordEt = view.findViewById(R.id.et_password1);
        signupTv = view.findViewById(R.id.tv_signup);
    }

    @Override
    public void onUserLoggedIn() {
        navController.navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void showError(String message) {
//        Toast.makeText(requireContext(), "email or password is incorrect.", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText("email or password is incorrect.");

        Toast toast = new Toast(requireContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}