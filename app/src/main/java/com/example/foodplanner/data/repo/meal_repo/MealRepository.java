package com.example.foodplanner.data.repo.meal_repo;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealRepository {
    Single<List<Meal>>  getRandomMeal();
    Single<List<Meal>> getVariousRandomMeals();
    Single<List<Meal>> getCountries();
    Single<List<Ingredient>>  getIngredients();

    Single<List<Meal>> getMealById(String mealId);

    Single<List<Meal>> getMealsByCategory(String category);
    Single<List<Meal>> getMealsByIngredient(String ingredient);
    Single<List<Meal>> getMealsByCountry(String country);


    Flowable<List<Meal>> getFavStoredMeals();

    Completable insertAllMeals(List<Meal> meals);
    Completable insertMeal(Meal meal);

    Completable deleteMeal(Meal meal);

    Completable clearDatabase();

}
