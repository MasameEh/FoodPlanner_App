package com.example.foodplanner.data.repo;


import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface FirebaseRepository {

    void saveUserId(String userId);

    String getUserId();
    FirebaseUser getCurrentUser();

    void clearUserData();

    Single<String> loginUser(String email, String password);
    Single<String> registerUser(String email, String password, String username);
    Single<FirebaseUser> signInWithGoogle(String tokenId);


    Completable logoutUser();
}
