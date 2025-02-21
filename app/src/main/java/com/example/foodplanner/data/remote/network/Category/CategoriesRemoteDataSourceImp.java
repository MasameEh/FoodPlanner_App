package com.example.foodplanner.data.remote.network.Category;

import android.util.Log;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.remote.network.MealService;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
    public Single<List<Category>> getAllCategories(){
        Single<CategoryResponse> categoriesCall = mealService.getCategories();

        return categoriesCall.map(item -> item.getCategories());

    }
}
