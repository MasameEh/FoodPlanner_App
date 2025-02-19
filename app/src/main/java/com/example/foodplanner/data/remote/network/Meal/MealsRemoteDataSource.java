package com.example.foodplanner.data.remote.network.Meal;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;
import com.example.foodplanner.data.remote.network.IngredientCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSource {

    Single<List<Meal>> getVariousRandomMeals();
    Single<List<Meal>> getRandomMeal();

    Single<List<Meal>> getCountries();

    Single<List<Ingredient>>  getIngredients();

    Single<List<Meal>> getMealById(String mealId) ;
    Single<List<Meal>> getMealsByCategory(String category);
    Single<List<Meal>> getMealsByIngredient(String ingredient);
    Single<List<Meal>> getMealsByCountry(String country);
}
