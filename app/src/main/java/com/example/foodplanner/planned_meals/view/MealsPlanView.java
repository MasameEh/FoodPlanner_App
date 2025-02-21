package com.example.foodplanner.planned_meals.view;

import com.example.foodplanner.data.model.MealPlan;

import java.util.List;

public interface MealsPlanView {

    void showMealForDay(List<MealPlan> meals);
    void showError(String err);
}
