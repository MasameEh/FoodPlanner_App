package com.example.foodplanner.fav_meals.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.repo.fav_meal_repo.MealRepository;
import com.example.foodplanner.fav_meals.view.FavoriteView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealsPresenterImp implements FavoriteMealsPresenter{

    MealRepository mealRepo;

    FavoriteView favoriteView;

    public FavoriteMealsPresenterImp(FavoriteView favoriteView, MealRepository mealRepo) {
        this.favoriteView = favoriteView;
        this.mealRepo = mealRepo;
    }

    public void getAllFavMeals(){
        mealRepo.getFavStoredMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> favoriteView.showFavMeals(meals),
                        throwable -> favoriteView.showError(throwable.getMessage())
                );
    }

    @Override
    public void removeFromFavoriteMeals(Meal meal) {
        mealRepo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () ->  favoriteView.showError("Removed from favorite"),
                        throwable -> favoriteView.showError(throwable.getMessage())
                );
    }
}
