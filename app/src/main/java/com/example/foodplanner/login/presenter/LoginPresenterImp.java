package com.example.foodplanner.login.presenter;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.login.view.LoginView;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenterImp implements LoginPresenter {

    private FirebaseRepository authRepo;

    private LoginView loginView;

    public LoginPresenterImp(FirebaseRepository authRepo, LoginView loginView) {
        this.authRepo = authRepo;
        this.loginView = loginView;
    }

    @Override
    public void loginUser(String email, String password) {
        Disposable subscribe = authRepo.loginUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (s) -> {
                            authRepo.saveUserId(s);
                            loginView.onUserLoggedIn();
                        },
                        throwable -> loginView.showError(throwable.getMessage())
                );
    }

}
