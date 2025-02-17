package com.example.foodplanner.data.remote.network.Category;

import android.util.Log;

import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.remote.network.MealService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesRemoteDataSourceImp implements CategoriesRemoteDataSource {

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    private static CategoriesRemoteDataSourceImp instance;

    private final MealService mealService;

    private static final String TAG = "CategoriesRemoteDataSourceImp";

    public CategoriesRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to Java objects
                .build();

        mealService = retrofit.create(MealService.class);
    }

    public static CategoriesRemoteDataSourceImp getInstance() {
        if (instance == null) {
            instance = new CategoriesRemoteDataSourceImp();
        }
        return instance;
    }

    @Override
    public void getAllCategories(CategoryCallBack networkCallBack){
        Call<CategoryResponse> Categoriescall = mealService.getCategories();

        Categoriescall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getCategories());
                if (response.isSuccessful() && response.body() != null){
                    networkCallBack.onSuccessCategory(response.body().getCategories());
                }{
                    networkCallBack.onFailureCategory(response.message());
                }

            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                Log.i(TAG, "onFailure: ");
                networkCallBack.onFailureCategory(throwable.getMessage());
            }
        });
    }
}
