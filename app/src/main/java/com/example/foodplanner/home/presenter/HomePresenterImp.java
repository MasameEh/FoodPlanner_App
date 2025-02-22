package com.example.foodplanner.home.presenter;

import com.example.foodplanner.data.repo.fav_meal_repo.MealRepository;
import com.example.foodplanner.home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter{

    private HomeView homeView;
    private MealRepository mealRepo;

    private static final String TAG = "HomePresenterImp";
    public HomePresenterImp(HomeView homeView, MealRepository mealRepo) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
    }

    @Override
    public void getRandomMeal() {
        mealRepo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.showRandomMealData(meals),
                        throwable -> homeView.showErr(throwable.getMessage())
                );
    }

    public void getVariousRandomMeals() {
        mealRepo.getVariousRandomMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> homeView.showRandomMealsData(meals),
                        throwable -> homeView.showErr(throwable.getMessage())
                );

    }



}
