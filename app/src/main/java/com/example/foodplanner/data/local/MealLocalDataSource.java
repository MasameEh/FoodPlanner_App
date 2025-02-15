package com.example.foodplanner.data.local;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.local.db.db.AppDatabase;
import com.example.foodplanner.data.local.db.db.MealDAO;
import com.example.foodplanner.data.model.Meal;

import java.util.List;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;

    private LiveData<List<Meal>> favProducts;
    @SuppressLint("StaticFieldLeak")
    private static MealLocalDataSource repo = null;

    private MealLocalDataSource(Context context){
        this.context = context;
        AppDatabase database = AppDatabase.getInstance(context);
        mealDAO = database.getProductDAO();
        favProducts = mealDAO.getAllFavProducts();
    }

    public static MealLocalDataSource getInstance(Context context){
        if(repo == null){
            repo =  new MealLocalDataSource(context);
        }
        return repo;
    }

    public void delete(Meal meal){
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.deleteMeal(meal);
            }
        }.start();
    }

    public void insert(Meal meal){
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.deleteMeal(meal);
            }
        }.start();
    }
}
