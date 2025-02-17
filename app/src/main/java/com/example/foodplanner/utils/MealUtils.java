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
            try {
                // Use reflection to dynamically access ingredient and measure fields
                Field ingredientField = Meal.class.getDeclaredField("strIngredient" + i);
                Field measureField = Meal.class.getDeclaredField("strMeasure" + i);

                // Ensure the fields are accessible
                ingredientField.setAccessible(true);
                measureField.setAccessible(true);

                // Retrieve values from the meal object
                String ingredientName = (String) ingredientField.get(meal);
                String measure = (String) measureField.get(meal);

                Log.d(TAG, "Extracting Ingredient: " + ingredientName + " - " + measure);

                // Add only non-empty ingredients with measures
                if (ingredientName != null && !ingredientName.trim().isEmpty() &&
                        measure != null && !measure.trim().isEmpty()) {
                    ingredientsList.add(new Ingredient(ingredientName, measure));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Log.e(TAG, "Error extracting ingredient " + i, e);
            }
        }

        Log.d(TAG, "Final Ingredient List Size: " + ingredientsList.size());
        return ingredientsList;
    }
}
