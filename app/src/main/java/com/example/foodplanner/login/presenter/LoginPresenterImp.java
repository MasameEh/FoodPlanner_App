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
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.Completable;
import android.util.Pair;

public class LoginPresenterImp implements LoginPresenter {

    private final FirebaseRepository authRepo;
    private final LoginView loginView;
    private final MealRepository mealRepo;
    private  MealPlanRepository mealPlanRepo;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LoginPresenterImp(FirebaseRepository authRepo,
                             LoginView loginView, MealRepository mealRepo,MealPlanRepository mealPlanRepo) {
        this.authRepo = authRepo;
        this.loginView = loginView;
        this.mealRepo = mealRepo;
        this.mealPlanRepo = mealPlanRepo;
    }

    @Override
    public void loginUser(String email, String password) {
        Disposable subscribe = authRepo.loginUser(email, password)
                .flatMap(userId -> {
                    Log.i("logggin", "Login success, userId: " + userId);
                    authRepo.saveUserId(userId);
                    return Single.zip(
                            authRepo.getFavoritesFromFirestore(),
                            authRepo.getPlansFromFirestore(),
                            (favorites, plans) -> {
                                Log.i("logggin", "Got data from Firestore - Favorites: " + 
                                        favorites.size() + ", Plans: " + plans.size());
                                return new Pair<>(favorites, plans);
                            }
                    );
                })
                .flatMapCompletable(pair -> {
                    return Completable.mergeArray(
                            mealRepo.insertAllMeals(pair.first),
                            mealPlanRepo.insertAllPlannedMeals(pair.second)
                    ).subscribeOn(Schedulers.io());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Log.i("logggin", "All data synced successfully");
                            loginView.onUserLoggedIn();
                        },
                        throwable -> {
                            Log.e("logggin", "Error syncing data: ", throwable);
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
