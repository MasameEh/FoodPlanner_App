package com.example.foodplanner.signup.presenter;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.signup.view.SignupView;
import com.google.firebase.auth.FirebaseUser;

public class SignupPresenterImp implements SignupPresenter, AuthCallback {
    private final FirebaseRepository authRepo;

    private final SignupView signupView;

    public SignupPresenterImp(FirebaseRepository authRepo, SignupView signupView) {
        this.authRepo = authRepo;
        this.signupView = signupView;
    }

    @Override
    public void registerUser(String email, String password, String username) {
        authRepo.registerUser(email, password, username,this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        authRepo.saveUserId(user.getUid());
        signupView.navigateToHome();
    }

    @Override
    public void onFailure(Exception e) {
        signupView.showError(e.getMessage());
    }
}
