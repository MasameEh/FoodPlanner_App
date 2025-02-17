package com.example.foodplanner.data.remote.network.Meal;


import android.util.Log;

import com.example.foodplanner.data.model.IngredientResponse;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.MealService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp implements MealsRemoteDataSource{

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealsRemoteDataSourceImp instance;

    private final MealService mealService;

    private static final String TAG = "MealsRemoteDataSource";
    private MealsRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to Java objects
                .build();

        mealService = retrofit.create(MealService.class);
    }
    public static MealsRemoteDataSourceImp getInstance() {
        if (instance == null) {
            instance = new MealsRemoteDataSourceImp();
        }
        return instance;
    }

    public void getVariousRandomMeals(NetworkCallBack<Meal> networkCallBack){
        Call<MealResponse> callRandomMeals = mealService.getMealsByName("");

        Log.i(TAG, "getVariousRandomMeals: ");
        callRandomMeals.enqueue(new Callback<MealResponse>() {

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

    public void getRandomMeal(NetworkCallBack<Meal> networkCallBack){
        Log.i(TAG, "getRandomMeal: ");
        Call<MealResponse> callRandomMeal = mealService.getRandomMeal();

        callRandomMeal.enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                //Log.i(TAG, "onResponse: " + response.body().getMeals());
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

    @Override
    public void getMealById(NetworkCallBack<Meal> networkCallBack, String mealId) {
        Log.i(TAG, "getMealById: ");
        Call<MealResponse> callMeal = mealService.getMealsById(mealId);

        callMeal.enqueue(new Callback<MealResponse>() {

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
}
