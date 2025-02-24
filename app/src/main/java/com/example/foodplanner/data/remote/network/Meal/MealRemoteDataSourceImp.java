package com.example.foodplanner.data.remote.network.Meal;


import android.util.Log;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.IngredientResponse;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;
import com.example.foodplanner.data.remote.network.MealService;
import com.example.foodplanner.utils.RandomMealsPicker;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MealRemoteDataSourceImp implements MealRemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealRemoteDataSourceImp instance;

    private final MealService mealService;

    private static final String TAG = "MealsRemoteDataSource";
    private MealRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to Java objects
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        mealService = retrofit.create(MealService.class);
    }

    public static MealRemoteDataSourceImp getInstance() {
        if (instance == null) {
            instance = new MealRemoteDataSourceImp();
        }
        return instance;
    }

    public Single<List<Meal>> getVariousRandomMeals(){
        Single<MealResponse> callRandomMeals = mealService.getMealsByCountry(RandomMealsPicker.getRandomMealArea());

        return callRandomMeals.map(item-> item.getMeals());
    }

    public Single<List<Meal>> getRandomMeal(){
        Log.i(TAG, "getRandomMeal: ");
        Single<MealResponse> callRandomMeal = mealService.getRandomMeal();
        return callRandomMeal.map(item-> item.getMeals());
    }


    public Single<List<Meal>> getCountries(){
        Log.i(TAG, "getCountries: ");
        Single<MealResponse> callCountries = mealService.getCountries();

        return callCountries.map(item-> item.getMeals());

    }

    public Single<List<Ingredient>> getIngredients(){
        Log.i(TAG, "getIngredients: ");
        Single<IngredientResponse> callIngredients = mealService.getIngredients();
        return callIngredients.map(item-> item.getIngredients());
    }

    @Override
    public Single<List<Meal>> getMealById(String mealId) {
        Log.i(TAG, "getMealById: ");
        Single<MealResponse> callMeal = mealService.getMealsById(mealId);
        return callMeal.map(item-> item.getMeals());
    }

    @Override
    public Single<List<Meal>> getMealsByCategory(String category) {
        Single<MealResponse> callMeals = mealService.getMealsByCategory(category);
        return callMeals.map(item-> item.getMeals());
    }

    @Override
    public Single<List<Meal>> getMealsByIngredient(String ingredient) {
        Single<MealResponse> callMeals = mealService.getMealsByMainIngredient(ingredient);
        return callMeals.map(item-> item.getMeals());
    }

    @Override
    public Single<List<Meal>> getMealsByCountry(String country) {
        Single<MealResponse> callMeals = mealService.getMealsByCountry(country);
        return callMeals.map(item-> item.getMeals());
    }
}
