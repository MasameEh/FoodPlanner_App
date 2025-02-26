package com.example.foodplanner.data.local.db.MealFavs;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDataSourceImp implements MealLocalDataSource {
    private final MealDAO mealDAO;
    private FavoriteDatabase database;
    private final Flowable<List<Meal>> favMeals;
    @SuppressLint("StaticFieldLeak")
    private static MealLocalDataSourceImp repo = null;

    private MealLocalDataSourceImp(Context context){
        database = FavoriteDatabase.getInstance(context);
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

    @Override
    public Completable clearDatabase() {
        return Completable.create(emitter -> {
                    database.clearAllTables();
                    emitter.onComplete();
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
