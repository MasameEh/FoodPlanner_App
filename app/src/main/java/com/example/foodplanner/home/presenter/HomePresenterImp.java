package com.example.foodplanner.home.presenter;

import com.example.foodplanner.data.repo.meal_repo.MealRepository;
import com.example.foodplanner.home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter{

    private final HomeView homeView;
    private final MealRepository mealRepo;

    private static final String TAG = "HomePresenterImp";
    public HomePresenterImp(HomeView homeView, MealRepository mealRepo) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
    }

    @Override
    public void getRandomMeal() {
        Disposable subscribe = mealRepo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.showRandomMealData(meals),
                        throwable -> homeView.showErr(throwable.getMessage())
                );
    }

    public void getVariousRandomMeals() {
        Disposable subscribe = mealRepo.getVariousRandomMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.showRandomMealsData(meals),
                        throwable -> homeView.showErr(throwable.getMessage())
                );

    }



}
