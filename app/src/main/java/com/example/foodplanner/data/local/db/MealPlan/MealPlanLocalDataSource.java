package com.example.foodplanner.data.local.db.MealPlan;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealPlanLocalDataSource {
    Flowable<List<MealPlan>> getMealsForDay(long selectedDate);

    Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate);
    Single<List<MealPlan>> getAllPlannedMeals();

    Completable insertAllPlannedMeals(List<MealPlan> mealPlans);
    Completable insertMealPlan(MealPlan mealPlan);


    Completable deleteMealPlan(MealPlan mealPlan);
}
