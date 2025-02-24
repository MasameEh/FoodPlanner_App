package com.example.foodplanner.data.remote.auth;

import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface AuthService {
    Single<String> login(String email, String password);

    Single<String> signUpWithEmail(String email, String password, String username);

    Single<FirebaseUser> signInWithGoogle(String idToken);

    Completable logout();
    FirebaseUser getCurrentUser();
}
