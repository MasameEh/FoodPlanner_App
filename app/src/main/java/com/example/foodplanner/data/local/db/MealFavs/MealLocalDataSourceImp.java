package com.example.foodplanner.data.local.db.MealFavs;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImp implements MealLocalDataSource {
    private final MealDAO mealDAO;

    private final Flowable<List<Meal>> favMeals;
    @SuppressLint("StaticFieldLeak")
    private static MealLocalDataSourceImp repo = null;

    private MealLocalDataSourceImp(Context context){
        FavoriteDatabase database = FavoriteDatabase.getInstance(context);
        mealDAO = database.getMealDAO();
        favMeals = mealDAO.getAllFavProducts();
    }

    public static MealLocalDataSourceImp getInstance(Context context){
        if(repo == null){
            repo =  new MealLocalDataSourceImp(context);
        }
        return repo;
    }

    public Flowable<List<Meal>> getFavStoredMeals(){
        return favMeals;
    }

    @Override
    public Completable insertAllMeals(List<Meal> meals) {
        return mealDAO.insertAllMeals(meals);
    }

    public Completable delete(Meal meal){
        return  mealDAO.deleteMeal(meal);
    }

    public Completable insert(Meal meal){
        return mealDAO.insertMeal(meal);
    }
}
