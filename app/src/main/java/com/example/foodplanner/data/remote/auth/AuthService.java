package com.example.foodplanner.data.remote.auth;

import com.google.firebase.auth.FirebaseUser;

public interface AuthService {
    void login(String email, String password, AuthCallback callback);

    void signUpWithEmail(String email, String password, String username, AuthCallback callback);

    void signInWithGoogle(String idToken, AuthCallback callback);

    void logout(AuthCallback callback);
    FirebaseUser getCurrentUser();
}
