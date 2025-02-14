package com.example.foodplanner.data.remote.network;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.MealResponse;

import retrofit2.Call;

public interface CategoriesRemoteDataSource {
    public void getAllCategories(CategoryCallBack networkCallBack);

}
