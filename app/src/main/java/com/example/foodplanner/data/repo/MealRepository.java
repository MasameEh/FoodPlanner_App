package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealRepository {
    Single<List<Meal>>  getRandomMeal();
    Single<List<Meal>> getVariousRandomMeals();
    Single<List<Meal>> getCountries();
    Single<List<Ingredient>>  getIngredients();

    Single<List<Meal>> getMealById(String mealId);
}
