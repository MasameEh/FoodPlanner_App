package com.example.foodplanner.data.repo.meal_plan_repo;

import com.example.foodplanner.data.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealPlanRepository {

    Flowable<List<MealPlan>> getMealsForDay(long selectedDate);

    Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate);

    Completable insertMealPlan(MealPlan mealPlan);


    Completable deleteMealPlan(MealPlan mealPlan);
}
