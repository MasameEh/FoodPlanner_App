package com.example.foodplanner.data.repo;


import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.google.firebase.auth.FirebaseUser;

public interface FirebaseRepository {

    void saveUserId(String userId);

    String getUserId();
    FirebaseUser getCurrentUser();

    void clearUserData();

    void loginUser(String email, String password, AuthCallback callback);
    void registerUser(String email, String password, String username, AuthCallback callback);
    void signInWithGoogle(String tokenId, AuthCallback callback);


    void logoutUser(AuthCallback callback);
}
