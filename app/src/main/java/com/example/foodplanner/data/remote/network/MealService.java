package com.example.foodplanner.data.remote.network;

import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.model.IngredientResponse;
import com.example.foodplanner.data.model.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("categories.php")
    Single<CategoryResponse> getCategories();

    @GET("list.php?a=list")
    Single<MealResponse> getCountries();

    @GET("list.php?i=list")
    Single<IngredientResponse> getIngredients();
    @GET("random.php")
    Single<MealResponse> getRandomMeal();

    @GET("search.php")
    Single<MealResponse> getMealsByName(@Query("s") String name);

    @GET("search.php")
    Single<MealResponse> getMealsByFirstLetter(@Query("f") String letter);

    @GET("lookup.php")
    Single<MealResponse> getMealsById(@Query("i") String id);


    @GET("filter.php")
    Single<MealResponse> getMealsByMainIngredient(@Query("i") String name);

    @GET("filter.php")
    Single<MealResponse> getMealsByCategory(@Query("c") String name);

    @GET("filter.php")
    Single<MealResponse> getMealsByCountry(@Query("a") String name);



}
