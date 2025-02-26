package com.example.foodplanner.profile.presenter;

import com.example.foodplanner.data.repo.FirebaseRepository;

import com.example.foodplanner.data.repo.meal_repo.MealRepository;
import com.example.foodplanner.profile.view.ProfileView;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImp implements ProfilePresenter {


    private final FirebaseRepository authRepo;

    private final ProfileView profileView;

    private final MealRepository mealRepository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ProfilePresenterImp(FirebaseRepository authRepo, ProfileView profileView, MealRepository mealRepository) {
        this.authRepo = authRepo;
        this.profileView = profileView;

        this.mealRepository = mealRepository;
    }

    public FirebaseUser getCurrentUser(){
        return authRepo.getCurrentUser();
    }

    public void logoutUser(){
        Disposable subscribe = authRepo.logoutUser()
                .andThen(mealRepository.clearDatabase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {

                            authRepo.clearUserData();
                            profileView.navigateToLogin();
                        },
                        throwable -> profileView.showError(throwable.getMessage())
                );
        compositeDisposable.add(subscribe);
    }

    @Override
    public void clear(){
        compositeDisposable.clear();
    }

}
