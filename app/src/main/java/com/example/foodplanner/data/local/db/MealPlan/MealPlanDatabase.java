package com.example.foodplanner.data.local.db.MealPlan;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.data.model.MealPlan;

@Database(entities = {MealPlan.class}, version = 1)

public abstract class MealPlanDatabase extends RoomDatabase {

    private static MealPlanDatabase instance = null;
    public abstract MealPlanDAO getMealPlanDAO();

    public static MealPlanDatabase getInstance(Context _context) {
        if (instance == null){
            instance = Room.databaseBuilder(_context, MealPlanDatabase.class, "meal_Plan_DB").build();
        }
        return instance;
    }
}
