package com.example.foodplanner.profile.presenter;

import com.google.firebase.auth.FirebaseUser;

public interface ProfilePresenter {
    FirebaseUser getCurrentUser();
    void logoutUser();

    void clear();
}
