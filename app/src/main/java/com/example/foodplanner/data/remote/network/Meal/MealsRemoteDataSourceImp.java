package com.example.foodplanner.data.remote.network.Meal;


import android.util.Log;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.IngredientResponse;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.MealService;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        mealService = retrofit.create(MealService.class);
    }
    public static MealsRemoteDataSourceImp getInstance() {
        if (instance == null) {
            instance = new MealsRemoteDataSourceImp();
        }
        return instance;
    }

    public Single<List<Meal>> getVariousRandomMeals(){
        Single<MealResponse> callRandomMeals = mealService.getMealsByName("");

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
//        callRandomMeal.enqueue(new Callback<IngredientResponse>() {
//            @Override
//            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
//                Log.i(TAG, "onResponse: " + response.body().getIngredients());
//                if (response.isSuccessful()){
//                    networkCallBack.onSuccessIngredient(response.body().getIngredients());
//                }{
//                    networkCallBack.onFailureIngredient(response.message());
//                }
//
//            }
//            @Override
//            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
//                Log.i(TAG, "onFailure: ");
//                networkCallBack.onFailureIngredient(throwable.getMessage());
//            }
//        });
    }

    @Override
    public Single<List<Meal>> getMealById(String mealId) {
        Log.i(TAG, "getMealById: ");
        Single<MealResponse> callMeal = mealService.getMealsById(mealId);
        return callMeal.map(item-> item.getMeals());
//        callMeal.enqueue(new Callback<MealResponse>() {
//
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                Log.i(TAG, "onResponse: " + response.body().getMeals());
//                if (response.isSuccessful()){
//                    networkCallBack.onSuccessResult(response.body().getMeals());
//                }{
//                    networkCallBack.onFailureResult(response.message());
//                }
//
//            }
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//                Log.i(TAG, "onFailure: ");
//                networkCallBack.onFailureResult(throwable.getMessage());
//            }
//        });
    }
}
