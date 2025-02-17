package com.example.foodplanner.data.remote.network.Meal;


import java.util.List;

public interface NetworkCallBack<T>  {
    public void onSuccessResult(List<T> meals);
    public void onFailureResult(String errMsg);
}
