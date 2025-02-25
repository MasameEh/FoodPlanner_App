package com.example.foodplanner.data.local.db.MealFavs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM meals_table")
    Flowable<List<Meal>> getAllFavProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(Meal meal);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertAllMeals(List<Meal> meals);
    @Delete
    Completable deleteMeal(Meal meal);

}
