package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.remote.network.Category.CategoryCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CategoryRepository {
    Single<List<Category>> getCategories();

}
