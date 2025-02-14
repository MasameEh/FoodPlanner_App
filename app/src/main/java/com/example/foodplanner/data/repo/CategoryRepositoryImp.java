package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.remote.network.CategoriesRemoteDataSource;
import com.example.foodplanner.data.remote.network.CategoryCallBack;
import com.example.foodplanner.data.remote.network.MealsRemoteDataSource;
import com.example.foodplanner.data.remote.network.NetworkCallBack;

public class CategoryRepositoryImp implements CategoryRepository{

    private final CategoriesRemoteDataSource categoriesRemote;
    private static CategoryRepositoryImp repo = null;
    public CategoryRepositoryImp(CategoriesRemoteDataSource categoriesRemote) {
        this.categoriesRemote = categoriesRemote;
    }


    public static CategoryRepositoryImp getInstance(CategoriesRemoteDataSource categoriesRemote){
        if(repo==null){
            repo = new CategoryRepositoryImp(categoriesRemote);
        }

        return repo;
    }

    public void getCategories(CategoryCallBack networkCallBack) {
        categoriesRemote.getAllCategories(networkCallBack);
    }
}
