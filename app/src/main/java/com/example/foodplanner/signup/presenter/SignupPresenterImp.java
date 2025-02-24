package com.example.foodplanner.signup.presenter;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.signup.view.SignupView;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignupPresenterImp implements SignupPresenter {
    private final FirebaseRepository authRepo;

    private final SignupView signupView;

    public SignupPresenterImp(FirebaseRepository authRepo, SignupView signupView) {
        this.authRepo = authRepo;
        this.signupView = signupView;
    }

    @Override
    public void registerUser(String email, String password, String username) {
        Disposable subscribe = authRepo.registerUser(email, password, username).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (s) -> {
                            authRepo.saveUserId(s);
                            signupView.navigateToHome();
                        },
                        throwable -> signupView.showError(throwable.getMessage())
                );
    }

}
