package com.example.foodplanner.login.presenter;

import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.remote.auth.AuthService;
import com.example.foodplanner.data.repo.AuthRepository;
import com.example.foodplanner.data.repo.AuthRepositoryImp;
import com.example.foodplanner.login.view.LoginView;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp implements LoginPresenter, AuthCallback {

    private AuthRepository authRepo;

    private LoginView loginView;

    public LoginPresenterImp(AuthRepository authRepo, LoginView loginView) {
        this.authRepo = authRepo;
        this.loginView = loginView;
    }


    @Override
    public void loginUser(String email, String password) {
        authRepo.userLogin(email, password, this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        authRepo.saveUserId(user.getUid());
        loginView.onUserLoggedIn();
    }

    @Override
    public void onFailure(Exception e) {

    }
}
