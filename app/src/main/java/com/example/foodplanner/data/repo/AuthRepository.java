package com.example.foodplanner.data.repo;


import com.example.foodplanner.data.remote.auth.AuthCallback;

public interface AuthRepository {

    void saveUserId(String userId);

    String getUserId();

    void clearUserData();

    void userLogin(String email, String password, AuthCallback callback);

}
