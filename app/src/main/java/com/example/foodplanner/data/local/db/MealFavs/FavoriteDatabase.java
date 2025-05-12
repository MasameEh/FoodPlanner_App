package com.example.foodplanner.data.local.db.MealFavs;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;


@Database(entities = {Meal.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static FavoriteDatabase instance = null;


    public abstract MealDAO getMealDAO();
    public static FavoriteDatabase getInstance(Context _context) {
        if (instance == null){
            instance = Room.databaseBuilder(_context, FavoriteDatabase.class, "mealsDB").build();
        }
        return instance;
    }
}
