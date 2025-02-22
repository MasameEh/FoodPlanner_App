package com.example.foodplanner.search_meals.view;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface SearchMealView {

    void showMealsData(List<Meal> meals);
    void showFilteredMeals(List<Meal> meals);
    void showError(String message);
}
