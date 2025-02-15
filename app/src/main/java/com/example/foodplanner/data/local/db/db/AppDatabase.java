package com.example.foodplanner.data.local.db.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.data.model.Meal;


@Database(entities = {Meal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance = null;

    public abstract MealDAO getProductDAO();
    public static AppDatabase getInstance(Context _context) {
        if (instance == null){
            instance = Room.databaseBuilder(_context, AppDatabase.class, "mealsDB").build();
        }
        return instance;
    }
}
