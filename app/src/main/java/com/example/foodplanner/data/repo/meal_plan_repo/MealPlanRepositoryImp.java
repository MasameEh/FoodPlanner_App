package com.example.foodplanner.data.repo.meal_plan_repo;

import com.example.foodplanner.data.local.db.MealPlan.MealPlanLocalDataSource;
import com.example.foodplanner.data.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealPlanRepositoryImp implements MealPlanRepository{


    private final MealPlanLocalDataSource mealPlanLocal;
    private static MealPlanRepositoryImp repo = null;


    private MealPlanRepositoryImp(MealPlanLocalDataSource mealPlanLocal) {
        this.mealPlanLocal = mealPlanLocal;
    }

    public static MealPlanRepositoryImp getInstance(MealPlanLocalDataSource mealLocal){
        if(repo==null){
            repo = new MealPlanRepositoryImp(mealLocal);
        }

        return repo;
    }

    @Override
    public Flowable<List<MealPlan>> getMealsForDay(long selectedDate) {
        return mealPlanLocal.getMealsForDay(selectedDate);
    }

    @Override
    public Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate) {
        return mealPlanLocal.getMealsForWeek(startDate, endDate);
    }

    @Override
    public Completable insertMealPlan(MealPlan mealPlan) {
        return mealPlanLocal.insertMealPlan(mealPlan);
    }

    @Override
    public Completable deleteMealPlan(MealPlan mealPlan) {
        return mealPlanLocal.deleteMealPlan(mealPlan);
    }
}
