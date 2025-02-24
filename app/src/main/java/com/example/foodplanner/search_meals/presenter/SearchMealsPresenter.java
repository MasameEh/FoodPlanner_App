package com.example.foodplanner.search_meals.presenter;

public interface SearchMealsPresenter {

    void getMealsByCategory(String category);
    void getMealsByCountry(String country);
    void getMealsByIngredient(String ingredient);



    void getMealsData(String type, String name);

    void filterMeals(String query);
}
