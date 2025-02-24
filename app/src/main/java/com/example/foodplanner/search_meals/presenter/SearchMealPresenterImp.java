package com.example.foodplanner.search_meals.presenter;

import android.util.Log;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.repo.meal_repo.MealRepository;
import com.example.foodplanner.search_meals.view.SearchMealView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchMealPresenterImp implements SearchMealsPresenter{

    private final MealRepository mealRepo;

    private final SearchMealView searchView;

    private final List<Meal> meals = new ArrayList<>();


    private static final String TAG = "SearchMealPresenterImp";

    public SearchMealPresenterImp(MealRepository mealRepo, SearchMealView searchView) {
        this.mealRepo = mealRepo;
        this.searchView = searchView;
    }


    @Override
    public void getMealsByCategory(String category) {
        Disposable subscribe = mealRepo.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            this.meals.addAll(meals);
                            searchView.showMealsData(meals);
                        },
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByCountry(String country) {
        Disposable subscribe = mealRepo.getMealsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            this.meals.addAll(meals);
                            Log.i(TAG, "getMealsByCountry: " + this.meals);
                            searchView.showMealsData(meals);
                        },
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByIngredient(String ingredient) {
        Disposable subscribe = mealRepo.getMealsByIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals ->{
                            this.meals.addAll(meals);
                            searchView.showMealsData(meals);
                        } ,
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsData(String type, String name) {
        switch(type){
            case "category":
                getMealsByCategory(name);
                break;
            case "country":
                getMealsByCountry(name);
                break;
            case "ingredient":
                getMealsByIngredient(name);
                break;
        }
    }

    @Override
    public void filterMeals(String query) {
        List<Meal> filtered = new ArrayList<>();
        Log.i(TAG, "filterMeals: " + meals);
        if (query.isEmpty()) {
            filtered.addAll(meals);
        } else {
            String filterPattern = query.toLowerCase().trim();
            for (Meal meal : meals) {
                if (meal.getMealName().toLowerCase().contains(filterPattern)) {

                    filtered.add(meal);
                }
            }
        }
        searchView.showFilteredMeals(filtered);
    }
}
