package com.example.foodplanner.data.repo.category_repo;

import com.example.foodplanner.data.model.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CategoryRepository {
    Single<List<Category>> getCategories();

}
