package com.example.foodplanner.home.view;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface HomeView {
    void showRandomMealData(List<Meal> meals);
    void showRandomMealsData(List<Meal> meals);
    void showErr(String err);
    void updateBookmarkIcon(boolean isBookmarked);

}
