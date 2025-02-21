package com.example.foodplanner.search_meals.presenter;

import com.example.foodplanner.data.repo.fav_meal_repo.MealRepository;
import com.example.foodplanner.search_meals.view.SearchMealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchMealPresenterImp implements SearchMealsPresenter{

    MealRepository mealRepo;

    SearchMealView searchView;

    public SearchMealPresenterImp(MealRepository mealRepo, SearchMealView searchView) {
        this.mealRepo = mealRepo;
        this.searchView = searchView;
    }


    @Override
    public void getMealsByCategory(String category) {
        mealRepo.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchView.showDate(meals),
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByCountry(String country) {
        mealRepo.getMealsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchView.showDate(meals),
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByIngredient(String ingredient) {
        mealRepo.getMealsByIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> searchView.showDate(meals),
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }
}
