package com.example.foodplanner.search_meals.presenter;

import android.util.Log;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Category.CategoryCallBack;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;
import com.example.foodplanner.data.repo.CategoryRepository;
import com.example.foodplanner.data.repo.MealRepository;
import com.example.foodplanner.search_meals.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {

    SearchView searchview;

    CategoryRepository categoryRepo;
    MealRepository mealRepo;

    private List<Meal> countries = new ArrayList<>();
    private List<Category> categories;
    private List<Ingredient> ingredients = new ArrayList<>();

    private static final String TAG = "SearchPresenterImp";

    public SearchPresenterImp(CategoryRepository categoryRepo, MealRepository mealRepo, SearchView searchview) {
        this.categoryRepo = categoryRepo;
        this.mealRepo = mealRepo;
        this.searchview = searchview;
    }


    @Override
    public void getCategories() {
        categoryRepo.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    categories1 -> {
                        this.categories = new ArrayList<>(categories1);
                        searchview.showCategories(categories1);
                    }    ,
                    throwable ->  searchview.showErr(throwable.getMessage())
                );
    }

    @Override
    public void getCountries() {
       mealRepo.getCountries()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       countries -> {
                           searchview.showCountries(countries);
                       },
                       throwable -> searchview.showErr(throwable.getMessage())
               );
    }

    @Override
    public void getIngredients() {
        mealRepo.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredients -> {
                            searchview.showIngredients(ingredients);
                        },
                        throwable -> searchview.showErr(throwable.getMessage())
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
        searchview.showFilteredCountries(filtered);
    }

    @Override
    public void filterCategories(String query) {
        List<Category> filtered = new ArrayList<>();


        Log.i(TAG, "Original Categories Size: " + categories.size());

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
        searchview.showFilteredCategories(filtered);
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
        searchview.showFilteredIngredients(filtered);
    }

}
