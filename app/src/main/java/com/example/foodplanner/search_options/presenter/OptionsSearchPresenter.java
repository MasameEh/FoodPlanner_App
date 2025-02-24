package com.example.foodplanner.search_options.presenter;

public interface OptionsSearchPresenter {
    void getCategories();
    void getCountries();
    void getIngredients();

    void filterCountries(String query);
    void filterCategories(String query);
    void filterIngredients(String query);

    void clear();

}
