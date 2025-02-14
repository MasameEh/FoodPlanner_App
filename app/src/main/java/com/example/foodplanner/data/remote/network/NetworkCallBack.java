package com.example.foodplanner.data.remote.network;


import com.example.foodplanner.data.model.Meal;

import java.util.List;

public interface NetworkCallBack<T>  {
    public void onSuccessResult(List<T> meals);
    public void onFailureResult(String errMsg);
}
