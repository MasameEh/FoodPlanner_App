package com.example.foodplanner.data.local.db.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.foodplanner.data.model.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllFavProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal product);

    @Delete
    void deleteMeal(Meal product);

}
