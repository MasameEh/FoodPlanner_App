package com.example.foodplanner.data.remote.network;

import com.example.foodplanner.data.model.Ingredient;

import java.util.List;

public interface IngredientCallBack{
    public void onSuccessIngredient(List<Ingredient> meals);
    public void onFailureIngredient(String errMsg);
}
