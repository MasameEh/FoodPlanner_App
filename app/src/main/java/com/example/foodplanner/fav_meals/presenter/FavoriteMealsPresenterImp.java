package com.example.foodplanner.fav_meals.presenter;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.repo.meal_repo.MealRepository;
import com.example.foodplanner.fav_meals.view.FavoriteView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealsPresenterImp implements FavoriteMealsPresenter{

    MealRepository mealRepo;

    FavoriteView favoriteView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public FavoriteMealsPresenterImp(FavoriteView favoriteView, MealRepository mealRepo) {
        this.favoriteView = favoriteView;
        this.mealRepo = mealRepo;
    }

    public void getAllFavMeals(){
        Disposable subscribe = mealRepo.getFavStoredMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> favoriteView.showFavMeals(meals),
                        throwable -> favoriteView.showError(throwable.getMessage())
                );

        compositeDisposable.add(subscribe);
    }

    @Override
    public void removeFromFavoriteMeals(Meal meal) {
        Disposable subscribe = mealRepo.deleteMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () ->  favoriteView.showError("Removed from favorite"),
                        throwable -> favoriteView.showError(throwable.getMessage())
                );
        compositeDisposable.add(subscribe);
    }

    @Override
    public void clear(){
        compositeDisposable.clear();
    }
}
