package com.example.foodplanner.fav_meals.view;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface FavoriteView {

    void showFavMeals(List<Meal> meals);
    void showError(String err);
}
