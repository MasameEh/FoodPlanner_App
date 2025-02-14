package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.remote.network.NetworkCallBack;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack networkCallBack);
}
