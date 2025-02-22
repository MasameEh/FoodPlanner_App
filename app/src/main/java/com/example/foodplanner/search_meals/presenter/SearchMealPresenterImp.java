package com.example.foodplanner.search_meals.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.repo.fav_meal_repo.MealRepository;
import com.example.foodplanner.search_meals.view.SearchMealView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchMealPresenterImp implements SearchMealsPresenter{

    private final MealRepository mealRepo;

    private final SearchMealView searchView;

    private List<Meal> meals;

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
                        meals -> {
                            this.meals = new ArrayList<>(meals);
                            searchView.showMealsData(meals);
                        },
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByCountry(String country) {
        mealRepo.getMealsByCountry(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> {
                            this.meals = new ArrayList<>(meals);
                            searchView.showMealsData(meals);
                        },
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByIngredient(String ingredient) {
        mealRepo.getMealsByIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals ->{
                            this.meals = new ArrayList<>(meals);
                            searchView.showMealsData(meals);
                        } ,
                        throwable -> searchView.showError(throwable.getMessage())
                );
    }

    @Override
    public void filterMeals(String query) {
        List<Meal> filtered = new ArrayList<>();

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
