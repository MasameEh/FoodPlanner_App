package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.Meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MealsRepositoryImp implements MealRepository{
    private final MealsRemoteDataSourceImp mealsRemote;

    private static MealsRepositoryImp repo = null;

    private MealsRepositoryImp(MealsRemoteDataSourceImp mealsRemote) {
        this.mealsRemote = mealsRemote;
    }


    public static MealsRepositoryImp getInstance(MealsRemoteDataSourceImp mealsRemote){
        if(repo==null){
            repo = new MealsRepositoryImp(mealsRemote);
        }

        return repo;
    }
    @Override
    public Single<List<Meal>>  getRandomMeal(){
        return mealsRemote.getRandomMeal();
    }

    @Override
    public Single<List<Meal>> getCountries() {
        return mealsRemote.getCountries();
    }

    @Override
    public Single<List<Ingredient>>  getIngredients() {
        return mealsRemote.getIngredients();
    }

    @Override
    public Single<List<Meal>> getMealById(String mealId) {
        return mealsRemote.getMealById( mealId);
    }

    @Override
    public Single<List<Meal>> getMealsByCategory(String category) {
        return mealsRemote.getMealsByCategory(category);
    }

    @Override
    public Single<List<Meal>> getMealsByIngredient(String ingredient) {
        return mealsRemote.getMealsByIngredient(ingredient);
    }

    @Override
    public Single<List<Meal>> getMealsByCountry(String country) {
        return mealsRemote.getMealsByCountry(country);
    }

    public Single<List<Meal>> getVariousRandomMeals(){
        return mealsRemote.getVariousRandomMeals();
    }

}
