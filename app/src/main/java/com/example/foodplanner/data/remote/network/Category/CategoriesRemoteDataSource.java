package com.example.foodplanner.data.remote.network.Category;

import com.example.foodplanner.data.model.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CategoriesRemoteDataSource {
    public Single<List<Category>> getAllCategories();

}
