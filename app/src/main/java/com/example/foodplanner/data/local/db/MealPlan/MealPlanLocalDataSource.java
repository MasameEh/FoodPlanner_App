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

public interface MealPlanLocalDataSource {
    Flowable<List<MealPlan>> getMealsForDay(long selectedDate);

    Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate);

    Completable insertMealPlan(MealPlan mealPlan);


    Completable deleteMealPlan(MealPlan mealPlan);
}
