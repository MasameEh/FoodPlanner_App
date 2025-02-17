package com.example.foodplanner.profile.presenter;

import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.repo.AuthRepository;
import com.example.foodplanner.profile.view.ProfileView;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePresenterImp implements ProfilePresenter, AuthCallback {


    private final AuthRepository authRepo;

    private final ProfileView profileView;

    public ProfilePresenterImp(AuthRepository authRepo, ProfileView profileView) {
        this.authRepo = authRepo;
        this.profileView = profileView;
    }

    public void logoutUser(){
        authRepo.logoutUser(this);
    }

    @Override
    public void onSuccess(FirebaseUser user) {
        authRepo.clearUserData();
        profileView.navigateToLogin();
    }

    @Override
    public void onFailure(Exception e) {
        profileView.showError(e.getMessage());
    }
}
