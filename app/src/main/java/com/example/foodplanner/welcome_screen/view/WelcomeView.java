package com.example.foodplanner.welcome_screen.view;

import com.google.firebase.auth.FirebaseUser;

public interface WelcomeView {
    void onGoogleSignInSuccess(FirebaseUser user);
    void onGoogleSignInFailure(String errorMessage);
}
