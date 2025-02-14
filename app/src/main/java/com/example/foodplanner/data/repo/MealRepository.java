package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.NetworkCallBack;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack<Meal> networkCallBack);
    void getCountries(NetworkCallBack<Meal> networkCallBack);
    void getIngredients(IngredientCallBack networkCallBack);
}
