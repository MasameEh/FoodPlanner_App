package com.example.foodplanner.meal_details.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;
import com.example.foodplanner.data.repo.MealRepository;
import com.example.foodplanner.meal_details.view.MealDetailsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter, NetworkCallBack<Meal> {


    MealDetailsView mealDetailsView;

    MealRepository mealRepo;


    public MealDetailsPresenterImp(MealRepository mealRepo, MealDetailsView mealDetailsView) {
        this.mealRepo = mealRepo;
        this.mealDetailsView = mealDetailsView;
    }

    public void getMealDetails(String mealId){
        mealRepo.getMealById(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> mealDetailsView.showMealDetails(meals),
                        throwable -> mealDetailsView.showError(throwable.getMessage())
                );
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
