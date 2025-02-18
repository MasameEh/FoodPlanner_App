package com.example.foodplanner.home.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.repo.MealRepository;
import com.example.foodplanner.home.view.HomeView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImp implements HomePresenter{

    private boolean isBookmarked = false;
    HomeView homeView;
    MealRepository mealRepo;

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
        //mealRepo.getVariousRandomMeals(this);
    }
    @Override
    public void addToFavoriteMeals() {

    }


    public void toggleBookmark() {
        isBookmarked = !isBookmarked;
        homeView.updateBookmarkIcon(isBookmarked);
    }



}
