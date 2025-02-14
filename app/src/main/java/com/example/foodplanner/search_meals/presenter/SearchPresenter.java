package com.example.foodplanner.search_meals.presenter;

public interface SearchPresenter {
    void getCategories();
    void getCountries();
    void getIngredients();

    void filterCountries(String query);
    void filterCategories(String query);
    void filterIngredients(String query);

}
