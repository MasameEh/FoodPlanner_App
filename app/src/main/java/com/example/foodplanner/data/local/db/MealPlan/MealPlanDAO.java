package com.example.foodplanner.data.local.db.MealPlan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.data.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealPlanDAO {
    @Query("SELECT * FROM meal_plan_table WHERE date = :selectedDate")
    Flowable<List<MealPlan>> getMealsForDay(long selectedDate);

    // Get meals planned for a week
    @Query("SELECT * FROM meal_plan_table WHERE date >= :startDate AND date <= :endDate ORDER BY date ASC")
    Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMealPlan(MealPlan mealPlan);

    // Remove a meal from the plan
    @Delete
    Completable deleteMealPlan(MealPlan mealPlan);
}
