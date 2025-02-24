package com.example.foodplanner.welcome_screen.presenter;

import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.welcome_screen.view.WelcomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WelcomePresenterImp implements WelcomePresenter {

    WelcomeView welcomeView;

    FirebaseRepository authRepo;

    public WelcomePresenterImp(WelcomeView welcomeView, FirebaseRepository firebaseRemote) {
        this.welcomeView = welcomeView;
        this.authRepo = firebaseRemote;
    }

    @Override
    public void signInWithGoogle(String idToken) {

        Disposable subscribe = authRepo.signInWithGoogle(idToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            authRepo.saveUserId(user.getUid());
                            welcomeView.onGoogleSignInSuccess(user);
                        },
                        throwable -> welcomeView.onGoogleSignInFailure(throwable.getMessage())
                );
    }

}
