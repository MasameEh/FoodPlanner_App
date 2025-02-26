package com.example.foodplanner.data.local.db.MealFavs;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {

    Flowable<List<Meal>> getFavStoredMeals();

    Completable insertAllMeals(List<Meal> meals);
    Completable delete(Meal meal);
    Completable insert(Meal meal);

    Completable clearDatabase();
}
