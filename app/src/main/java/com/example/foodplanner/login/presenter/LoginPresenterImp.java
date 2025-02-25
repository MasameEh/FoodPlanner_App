package com.example.foodplanner.login.presenter;

import android.util.Log;

import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.data.repo.meal_plan_repo.MealPlanRepository;
import com.example.foodplanner.data.repo.meal_repo.MealRepository;
import com.example.foodplanner.login.view.LoginView;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenterImp implements LoginPresenter {

    private final FirebaseRepository authRepo;
    private final LoginView loginView;
    private final MealRepository mealRepo;
    private  MealPlanRepository mealPlanRepo;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LoginPresenterImp(FirebaseRepository authRepo,
                             LoginView loginView, MealRepository mealRepo) {
        this.authRepo = authRepo;
        this.loginView = loginView;
        this.mealRepo = mealRepo;
    }

    @Override
    public void loginUser(String email, String password) {
        Disposable subscribe = authRepo.loginUser(email, password)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(userId -> {
                    Log.i("logggin", "Login success, userId: " + userId);
                    authRepo.saveUserId(userId);
                })
                .flatMap(__ -> {
                    Log.i("logggin", "Getting favorites from Firestore");
                    return authRepo.getFavoritesFromFirestore();
                })
                .doOnSuccess(meals -> {
                    Log.i("logggin", "Got meals from Firestore: " );
                })
                .flatMapCompletable(meals -> {
                    Log.i("logggin", "Starting to insert meals: " );
                    return mealRepo.insertAllMeals(meals)
                            .subscribeOn(Schedulers.io());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Log.i("logggin", "All operations completed successfully");
                            loginView.onUserLoggedIn();
                        },
                        throwable -> {
                            Log.e("logggin", "Error occurred: ", throwable);
                            loginView.showError(throwable.getMessage());
                        }
                );
        compositeDisposable.add(subscribe);
    }

    @Override
    public void clear(){
        compositeDisposable.clear();;
    }
}
