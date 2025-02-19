package com.example.foodplanner.search_options.view;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface OptionsSearchView {

    void showCategories(List<Category> categories);
    void showCountries(List<Meal> countries);
    void showIngredients(List<Ingredient> meals);

    void showFilteredCountries(List<Meal> countries);
    void showFilteredCategories(List<Category> categories);
    void showFilteredIngredients(List<Ingredient> ingredients);
    void showErr(String err);
}
