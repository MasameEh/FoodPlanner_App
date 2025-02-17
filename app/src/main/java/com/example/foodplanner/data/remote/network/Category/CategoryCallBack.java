package com.example.foodplanner.data.remote.network.Category;

import com.example.foodplanner.data.model.Category;

import java.util.List;

public interface CategoryCallBack{
    public void onSuccessCategory(List<Category> meals);
    public void onFailureCategory(String errMsg);
}
