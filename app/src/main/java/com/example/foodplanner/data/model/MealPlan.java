package com.example.foodplanner.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_plan_table")
public class MealPlan {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mealId;
    private String mealName;
    private String mealImage;
    private Long date;  // Timestamp of the planned meal (milliseconds)


    public MealPlan(int id, String mealName, String mealId, String mealImage, Long date) {
        this.id = id;
        this.mealName = mealName;
        this.mealId = mealId;
        this.mealImage = mealImage;
        this.date = date;
    }

    @Ignore
    public MealPlan() {

    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public int getId() {
        return id;
    }

    public String getMealId() {
        return mealId;
    }

    public Long getDate() {
        return date;
    }


    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealPlan{" +
                "id=" + id +
                ", mealId='" + mealId + '\'' +
                ", mealName='" + mealName + '\'' +
                ", mealImage='" + mealImage + '\'' +
                ", date=" + date +
                '}';
    }
}

