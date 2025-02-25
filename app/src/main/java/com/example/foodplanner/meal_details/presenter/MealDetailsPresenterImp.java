package com.example.foodplanner.meal_details.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;
import com.example.foodplanner.data.repo.meal_plan_repo.MealPlanRepository;
import com.example.foodplanner.data.repo.meal_repo.MealRepository;
import com.example.foodplanner.meal_details.view.MealDetailsView;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter {


    MealDetailsView mealDetailsView;

    MealRepository mealRepo;
    MealPlanRepository mealPlanRepo;

    private boolean isClicked = false;
    public MealDetailsPresenterImp(MealRepository mealRepo,MealPlanRepository mealPlanRepo,MealDetailsView mealDetailsView) {
        this.mealRepo = mealRepo;
        this.mealDetailsView = mealDetailsView;
        this.mealPlanRepo = mealPlanRepo;
    }
    @Override
    public Disposable getMealDetails(String mealId){
        return mealRepo.getMealById(mealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> mealDetailsView.showMealDetails(meals),
                        throwable -> mealDetailsView.showError(throwable.getMessage())
                );
    }

    @Override
    public void toggleFavIcon() {
        isClicked = !isClicked;
        mealDetailsView.updateToggleIcon(isClicked);
    }

    @Override
    public Disposable addToFavoriteMeals(Meal meal) {
        return mealRepo.insertMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            mealDetailsView.showToast("Added to favorites");
                        },
                        throwable -> mealDetailsView.showError(throwable.getMessage())
                );
    }

    @Override
    public Disposable removeFromFavoriteMeals(Meal meal){
        return  mealRepo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> mealDetailsView.showError("Removed from favorite"),
                        throwable -> mealDetailsView.showError(throwable.getMessage())
                );


    }

    @Override
    public Disposable addMealToCalender(MealPlan mealPlan){

        return mealPlanRepo.insertMealPlan(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            mealDetailsView.showToast("Added to calender");
                        },
                        throwable -> {
                            mealDetailsView.showError(throwable.getMessage());
                        }
                );
    }

}
