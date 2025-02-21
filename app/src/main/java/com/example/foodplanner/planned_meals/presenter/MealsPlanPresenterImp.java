package com.example.foodplanner.planned_meals.presenter;

import com.example.foodplanner.data.model.MealPlan;
import com.example.foodplanner.data.repo.meal_plan_repo.MealPlanRepository;
import com.example.foodplanner.planned_meals.view.MealsPlanView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsPlanPresenterImp implements MealsPlanPresenter{

    private final MealPlanRepository mealPlanRepo;

    private final MealsPlanView mealsPlanView;

    public MealsPlanPresenterImp(MealPlanRepository mealPlanRepo, MealsPlanView mealsPlanView) {
        this.mealPlanRepo = mealPlanRepo;
        this.mealsPlanView = mealsPlanView;
    }

    @Override
    public Disposable getMealsForDay(long selectedDate) {
        return mealPlanRepo.getMealsForDay(selectedDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealPlans -> mealsPlanView.showMealForDay(mealPlans),
                        throwable -> mealsPlanView.showError(throwable.getMessage())
                );
    }

    @Override
    public Disposable removeMealsForDay(MealPlan mealPlan) {
        return mealPlanRepo.deleteMealPlan(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> mealsPlanView.showError("Successfully deleted"),
                        throwable -> mealsPlanView.showError(throwable.getMessage())
                );
    }
}
