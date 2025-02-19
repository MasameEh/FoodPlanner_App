package com.example.foodplanner.home.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;
import com.example.foodplanner.data.repo.MealRepository;
import com.example.foodplanner.home.view.HomeView;

import java.util.List;

public class HomePresenterImp implements HomePresenter, NetworkCallBack<Meal> {

    private boolean isBookmarked = false;
    HomeView homeView;
    MealRepository mealRepo;

    public HomePresenterImp(HomeView homeView, MealRepository mealRepo) {
        this.homeView = homeView;
        this.mealRepo = mealRepo;
    }

    @Override
    public void getRandomMeal() {
        mealRepo.getRandomMeal(this);
    }

    public void getVariousRandomMeals() {
        //mealRepo.getVariousRandomMeals(this);
    }
    @Override
    public void addToFavoriteMeals() {

    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        homeView.showRandomMealData(meals);
        //homeView.showRandomMealsData(meals);
    }

    @Override
    public void onFailureResult(String errMsg) {
        homeView.showErr(errMsg);
    }

    public void toggleBookmark() {
        isBookmarked = !isBookmarked;
        homeView.updateBookmarkIcon(isBookmarked);
    }



}
