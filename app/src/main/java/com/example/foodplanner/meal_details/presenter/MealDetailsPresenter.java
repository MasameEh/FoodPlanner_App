package com.example.foodplanner.meal_details.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

public interface  MealDetailsPresenter {
    Disposable getMealDetails(String mealId);
    void toggleFavIcon();
    Disposable addToFavoriteMeals(Meal meal);

    Disposable removeFromFavoriteMeals(Meal meal);

    Disposable addMealToCalender(MealPlan meal);

}
