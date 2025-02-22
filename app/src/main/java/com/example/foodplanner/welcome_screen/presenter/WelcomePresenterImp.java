package com.example.foodplanner.welcome_screen.presenter;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.welcome_screen.view.WelcomeView;
import com.google.firebase.auth.FirebaseUser;

public class WelcomePresenterImp implements WelcomePresenter, AuthCallback {

    WelcomeView welcomeView;

    FirebaseRepository authRepo;

    public WelcomePresenterImp(WelcomeView welcomeView, FirebaseRepository firebaseRemote) {
        this.welcomeView = welcomeView;
        this.authRepo = firebaseRemote;
    }

    @Override
    public void signInWithGoogle(String idToken) {

        authRepo.signInWithGoogle(idToken, this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        authRepo.saveUserId(user.getUid());
        welcomeView.onGoogleSignInSuccess(user);
    }

    @Override
    public void onFailure(Exception e) {
        welcomeView.onGoogleSignInFailure(e.getMessage());
    }
}
