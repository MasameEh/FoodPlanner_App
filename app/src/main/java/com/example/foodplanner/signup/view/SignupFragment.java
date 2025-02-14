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
import android.widget.Toast;

import com.example.foodplanner.R;

import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;
import com.example.foodplanner.data.repo.AuthRepositoryImp;
import com.example.foodplanner.signup.presenter.SignupPresenter;
import com.example.foodplanner.signup.presenter.SignupPresenterImp;
import com.example.foodplanner.utils.InputValidator;


public class SignupFragment extends Fragment implements SignupView{

    private static final String TAG = "SignUpScreen";
    EditText usernameEt;
    EditText emailEt;
    EditText passwordEt;
    EditText confirmPassEt;

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
        return inflater.inflate(R.layout.activity_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signupPresenter = new SignupPresenterImp(AuthRepositoryImp.getInstance(FirebaseRemoteDataSource.getInstance(),
                CacheHelper.getInstance(requireContext())), this);

        this.view = view;
        initializeUI(view);

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
    }

    private void createAccount(String email, String password){
//        authService.signUpWithEmail(email, password, new AuthCallback() {
//            @Override
//            public void onSuccess(FirebaseUser user) {
//                Log.d(TAG, "Sign-up successful");
////                cacheHelper.saveString("userId", user.getUid());
//                Toast.makeText(requireContext(), "Sign-up successful!", Toast.LENGTH_SHORT).show();
//                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_homeFragment);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                Log.w(TAG, "Sign-up failed", e);
//                Toast.makeText(requireContext(), "Sign-up failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void navigateToHome() {
        findNavController(view).navigate(R.id.action_signupFragment_to_homeFragment);
    }

    public void showError(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}