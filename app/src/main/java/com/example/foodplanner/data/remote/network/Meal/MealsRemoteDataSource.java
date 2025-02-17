package com.example.foodplanner.data.remote.network.Meal;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.IngredientCallBack;

public interface MealsRemoteDataSource {

    void getVariousRandomMeals(NetworkCallBack<Meal> networkCallBack);
    void getRandomMeal(NetworkCallBack<Meal> networkCallBack);

    void getCountries(NetworkCallBack<Meal> networkCallBack);

    void getIngredients(IngredientCallBack networkCallBack);

    void getMealById(NetworkCallBack<Meal> networkCallBack, String mealId);
}
