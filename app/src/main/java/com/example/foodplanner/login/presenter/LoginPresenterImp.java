package com.example.foodplanner.login.presenter;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.login.view.LoginView;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp implements LoginPresenter, AuthCallback {

    private FirebaseRepository authRepo;

    private LoginView loginView;

    public LoginPresenterImp(FirebaseRepository authRepo, LoginView loginView) {
        this.authRepo = authRepo;
        this.loginView = loginView;
    }

    @Override
    public void loginUser(String email, String password) {
        authRepo.loginUser(email, password, this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        authRepo.saveUserId(user.getUid());
        loginView.onUserLoggedIn();
    }

    @Override
    public void onFailure(Exception e) {
        loginView.showError(e.getMessage());
    }
}
