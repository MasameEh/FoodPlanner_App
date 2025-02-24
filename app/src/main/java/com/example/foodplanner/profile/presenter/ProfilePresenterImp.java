package com.example.foodplanner.profile.presenter;

import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.profile.view.ProfileView;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImp implements ProfilePresenter {


    private final FirebaseRepository authRepo;

    private final ProfileView profileView;

    public ProfilePresenterImp(FirebaseRepository authRepo, ProfileView profileView) {
        this.authRepo = authRepo;
        this.profileView = profileView;
    }

    public FirebaseUser getCurrentUser(){
        return authRepo.getCurrentUser();
    }

    public void logoutUser(){
        Disposable subscribe = authRepo.logoutUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            authRepo.clearUserData();
                            profileView.navigateToLogin();
                        },
                        throwable -> profileView.showError(throwable.getMessage())
                );
    }

}
