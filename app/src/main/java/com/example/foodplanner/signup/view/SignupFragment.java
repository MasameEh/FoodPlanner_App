package com.example.foodplanner.signup.view;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;

import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSourceImp;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.signup.presenter.SignupPresenter;
import com.example.foodplanner.signup.presenter.SignupPresenterImp;
import com.example.foodplanner.utils.InputValidator;


public class SignupFragment extends Fragment implements SignupView{

    private static final String TAG = "SignUpScreen";
    EditText usernameEt;
    EditText emailEt;
    EditText passwordEt;
    EditText confirmPassEt;

    TextView loginTv;
    Button sigUpBtn;
    private View view;

    SignupPresenter signupPresenter;

    public SignupFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signupPresenter = new SignupPresenterImp(
                FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                CacheHelper.getInstance(requireContext())), this);

        this.view = view;
        initializeUI(view);

        loginTv.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment);
        });
        sigUpBtn.setOnClickListener(v -> {
            String username  = usernameEt.getText().toString().trim();
            String email  = emailEt.getText().toString().trim();
            String password  = passwordEt.getText().toString().trim();
            String passwordC  = confirmPassEt.getText().toString().trim();
            if (InputValidator.validateSignupInputs(usernameEt, emailEt, passwordEt, confirmPassEt)) {
                signupPresenter.registerUser(email, password, username);
            }

        });
    }

    void initializeUI(View view){
        usernameEt = view.findViewById(R.id.et_username);
        emailEt = view.findViewById(R.id.et_email);
        passwordEt = view.findViewById(R.id.et_password);
        confirmPassEt = view.findViewById(R.id.et_confirmPass);
        sigUpBtn = view.findViewById(R.id.btn_signup);
        loginTv = view.findViewById(R.id.tv_login2);
    }


    @Override
    public void navigateToHome() {
        findNavController(view).navigate(R.id.action_signupFragment_to_homeFragment);
    }

    public void showError(String message){
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        Toast toast = new Toast(requireContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        signupPresenter.clear();
    }


}