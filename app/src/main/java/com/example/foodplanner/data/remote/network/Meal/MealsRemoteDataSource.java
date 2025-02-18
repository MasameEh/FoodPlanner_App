package com.example.foodplanner.data.remote.network.Meal;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;
import com.example.foodplanner.data.remote.network.IngredientCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSource {

    Single<List<Meal>> getVariousRandomMeals();
    public Single<List<Meal>> getRandomMeal();

    public Single<List<Meal>> getCountries();

    Single<List<Ingredient>>  getIngredients();

    public Single<List<Meal>> getMealById(String mealId) ;
}
