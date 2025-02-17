package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.remote.network.Category.CategoriesRemoteDataSource;
import com.example.foodplanner.data.remote.network.Category.CategoryCallBack;

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
