package com.example.foodplanner.search_options.presenter;

import android.util.Log;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.repo.CategoryRepository;
import com.example.foodplanner.data.repo.MealRepository;
import com.example.foodplanner.search_options.view.OptionsSearchView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OptionsSearchPresenterImp implements OptionsSearchPresenter {

    OptionsSearchView searchView;

    CategoryRepository categoryRepo;
    MealRepository mealRepo;

    private List<Meal> countries;
    private List<Category> categories;
    private List<Ingredient> ingredients;


    private static final String TAG = "SearchPresenterImp";

    public OptionsSearchPresenterImp(CategoryRepository categoryRepo, MealRepository mealRepo, OptionsSearchView searchview) {
        this.categoryRepo = categoryRepo;
        this.mealRepo = mealRepo;
        this.searchView = searchview;
    }


    @Override
    public void getCategories() {
        categoryRepo.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    categories1 -> {
                        this.categories = new ArrayList<>(categories1);
                        searchView.showCategories(categories1);
                    }    ,
                    throwable ->  searchView.showErr(throwable.getMessage())
                );
    }

    @Override
    public void getCountries() {
       mealRepo.getCountries()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       countries -> {
                           this.countries = new ArrayList<>(countries);
                           searchView.showCountries(countries);
                       },
                       throwable -> searchView.showErr(throwable.getMessage())
               );
    }

    @Override
    public void getIngredients() {
        mealRepo.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredients -> {
                            this.ingredients = new ArrayList<>(ingredients);
                            searchView.showIngredients(ingredients);
                        },
                        throwable -> searchView.showErr(throwable.getMessage())
                );;
    }

    @Override
    public void filterCountries(String query) {
        List<Meal> filtered = new ArrayList<>();

        if (query.isEmpty()) {
            filtered.addAll(countries);
        } else {
            String filterPattern = query.toLowerCase().trim();
            for (Meal meal : countries) {
                if (meal.getMealCountry().toLowerCase().contains(filterPattern)) {

                    filtered.add(meal);
                }
            }
        }
        searchView.showFilteredCountries(filtered);
    }

    @Override
    public void filterCategories(String query) {
        List<Category> filtered = new ArrayList<>();


        //Log.i(TAG, "Original Categories Size: " + categories.size());

        if (query.isEmpty()) {
            filtered.addAll(categories);
            Log.i(TAG, "Query is empty, showing all categories.");
        } else {
            String filterPattern = query.toLowerCase().trim();
            for (Category category : categories) {
                if (category.getCategoryName().toLowerCase().contains(filterPattern)) {

                    filtered.add(category);
                }
            }
        }
        Log.i(TAG, "Filtered Categories Count: " + filtered.size());
        searchView.showFilteredCategories(filtered);
    }

    @Override
    public void filterIngredients(String query) {
        List<Ingredient> filtered = new ArrayList<>();

        if (query.isEmpty()) {
            filtered.addAll(ingredients);
        } else {
            String filterPattern = query.toLowerCase().trim();
            for (Ingredient ingredient : ingredients) {
                if (ingredient.getName().toLowerCase().contains(filterPattern)) {

                    filtered.add(ingredient);
                }
            }
        }
        searchView.showFilteredIngredients(filtered);
    }

}
