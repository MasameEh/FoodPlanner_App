package com.example.foodplanner.search_meals.view;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface SearchMealView {

    void showDate(List<Meal> meals);
    void showError(String message);
}
