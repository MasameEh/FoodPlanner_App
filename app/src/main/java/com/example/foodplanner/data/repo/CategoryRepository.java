package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.remote.network.Category.CategoryCallBack;

public interface CategoryRepository {
    void getCategories(CategoryCallBack networkCallBack);

}
