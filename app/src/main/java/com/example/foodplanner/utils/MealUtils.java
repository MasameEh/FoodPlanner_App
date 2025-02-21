package com.example.foodplanner.utils;

import android.util.Log;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MealUtils {
    private static final String TAG = "MealUtils";

    public static List<Ingredient> getIngredientsList(Meal meal) {
        List<Ingredient> ingredientsList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {

            String ingredientName = meal.getStrIngredient(i);
            String measure = meal.getStrMeasure(i);

            Log.d(TAG, "Extracting Ingredient: " + ingredientName + " - " + measure);


            if (ingredientName != null && !ingredientName.trim().isEmpty() &&
                    measure != null && !measure.trim().isEmpty()) {
                ingredientsList.add(new Ingredient(ingredientName, measure));
            }
        }

        Log.d(TAG, "Final Ingredient List Size: " + ingredientsList.size());
        return ingredientsList;
    }
}
