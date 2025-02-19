package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack<Meal> networkCallBack);
    void getVariousRandomMeals(NetworkCallBack<Meal> networkCallBack);
    void getCountries(NetworkCallBack<Meal> networkCallBack);
    void getIngredients(IngredientCallBack networkCallBack);

    void getMealById(NetworkCallBack<Meal> networkCallBack, String mealId);
}
