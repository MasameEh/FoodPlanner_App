package com.example.foodplanner.data.remote.network;

import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.model.MealResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("search.php")
    Call<MealResponse> getMealsByName(@Query("s") String name);

    @GET("search.php")
    Call<MealResponse> getMealsByFirstLetter(@Query("f") String letter);

    @GET("lookup.php")
    Call<MealResponse> getMealsById(@Query("i") String id);


    @GET("filter.php")
    Call<MealResponse> getMealsByMainIngredient(@Query("i") String name);

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String name);

    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String name);

}
