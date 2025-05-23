package com.example.foodplanner.data.model;

import java.util.List;

public class MealResponse {
    private final List<Meal> meals;

    public MealResponse(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

}
