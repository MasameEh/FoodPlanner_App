package com.example.foodplanner.data.local.db.MealPlan;

import android.content.Context;

import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealPlanLocalDataSourceImp implements MealPlanLocalDataSource{


   private MealPlanDAO mealPlanDAO;

    private Flowable<List<MealPlan>> favProducts;

    private static MealPlanLocalDataSourceImp instance = null;
    public MealPlanLocalDataSourceImp(Context context) {
        MealPlanDatabase mealPlanDatabase = MealPlanDatabase.getInstance(context);
        mealPlanDAO = mealPlanDatabase.getMealPlanDAO();
    }

    public static MealPlanLocalDataSourceImp getInstance(Context context){
        if(instance == null){
            instance =  new MealPlanLocalDataSourceImp(context);
        }
        return instance;
    }

    @Override
    public Flowable<List<MealPlan>> getMealsForDay(long selectedDate) {
        return mealPlanDAO.getMealsForDay(selectedDate);
    }

    @Override
    public Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate) {
        return mealPlanDAO.getMealsForWeek(startDate, endDate);
    }

    @Override
    public Completable insertMealPlan(MealPlan mealPlan) {
        return mealPlanDAO.insertMealPlan(mealPlan);
    }

    @Override
    public Completable deleteMealPlan(MealPlan mealPlan) {
        return mealPlanDAO.deleteMealPlan(mealPlan);
    }
}
