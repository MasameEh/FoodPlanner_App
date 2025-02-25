package com.example.foodplanner.fav_meals.presenter;

import com.example.foodplanner.data.model.Meal;

public interface FavoriteMealsPresenter {

    void getAllFavMeals();
    void removeFromFavoriteMeals(Meal meal);

    void clear();
}
