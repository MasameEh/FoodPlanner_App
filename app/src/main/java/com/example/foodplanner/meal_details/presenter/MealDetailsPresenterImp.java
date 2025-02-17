package com.example.foodplanner.meal_details.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;
import com.example.foodplanner.data.repo.CategoryRepository;
import com.example.foodplanner.data.repo.MealRepository;
import com.example.foodplanner.meal_details.view.MealDetailsView;
import com.example.foodplanner.search_meals.view.SearchView;

import java.util.List;

public class MealDetailsPresenterImp implements MealDetailsPresenter, NetworkCallBack<Meal> {


    MealDetailsView mealDetailsView;

    MealRepository mealRepo;


    public MealDetailsPresenterImp(MealRepository mealRepo, MealDetailsView mealDetailsView) {
        this.mealRepo = mealRepo;
        this.mealDetailsView = mealDetailsView;
    }

    public void getMealDetails(String mealId){
        mealRepo.getMealById(this, mealId);
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        mealDetailsView.showMealDetails(meals);
    }

    @Override
    public void onFailureResult(String errMsg) {
        mealDetailsView.showError(errMsg);
    }

}
