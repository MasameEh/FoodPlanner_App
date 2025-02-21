package com.example.foodplanner.planned_meals.presenter;

import com.example.foodplanner.data.model.MealPlan;

import io.reactivex.rxjava3.disposables.Disposable;

public interface MealsPlanPresenter {
    Disposable getMealsForDay(long selectedDate);
    Disposable removeMealsForDay(MealPlan mealPlan);
}
