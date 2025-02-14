package com.example.foodplanner.data.remote.network;

import android.util.Log;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.IngredientResponse;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealsRemoteDataSource instance;

    private final MealService mealService;

    private static final String TAG = "MealsRemoteDataSource";
    private MealsRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to Java objects
                .build();

        mealService = retrofit.create(MealService.class);
    }
    public static MealsRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new MealsRemoteDataSource();
        }
        return instance;
    }

    public void getRandomMeal(NetworkCallBack<Meal> networkCallBack){
        Log.i(TAG, "getRandomMeal: ");
        Call<MealResponse> callRandomMeal = mealService.getRandomMeal();

        callRandomMeal.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getMeals());
                if (response.isSuccessful()){
                    networkCallBack.onSuccessResult(response.body().getMeals());
                }{
                    networkCallBack.onFailureResult(response.message());
                }

            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(throwable.getMessage());
            }
        });
    }


    public void getCountries(NetworkCallBack<Meal> networkCallBack){
        Log.i(TAG, "getCountries: ");
        Call<MealResponse> callRandomMeal = mealService.getCountries();

        callRandomMeal.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getMeals());
                if (response.isSuccessful()){
                    networkCallBack.onSuccessResult(response.body().getMeals());
                }{
                    networkCallBack.onFailureResult(response.message());
                }

            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureResult(throwable.getMessage());
            }
        });
    }

    public void getIngredients(IngredientCallBack networkCallBack){
        Log.i(TAG, "getIngredients: ");
        Call<IngredientResponse> callRandomMeal = mealService.getIngredients();

        callRandomMeal.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getIngredients());
                if (response.isSuccessful()){
                    networkCallBack.onSuccessIngredient(response.body().getIngredients());
                }{
                    networkCallBack.onFailureIngredient(response.message());
                }

            }
            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureIngredient(throwable.getMessage());
            }
        });
    }
}
