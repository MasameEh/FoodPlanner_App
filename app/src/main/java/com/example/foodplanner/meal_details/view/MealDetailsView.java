package com.example.foodplanner.meal_details.view;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface MealDetailsView {

    void showMealDetails(List<Meal> meals);
    void updateToggleIcon(boolean isClicked);

    void showToast(String msg);
    void showError(String err);
}
