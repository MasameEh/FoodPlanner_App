package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.IngredientCallBack;
import com.example.foodplanner.data.remote.network.Meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Meal.NetworkCallBack;

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
    public void getRandomMeal(NetworkCallBack<Meal> networkCallBack){
        mealsRemote.getRandomMeal(networkCallBack);
    }

    @Override
    public void getCountries(NetworkCallBack<Meal> networkCallBack) {
        mealsRemote.getCountries(networkCallBack);
    }

    @Override
    public void getIngredients(IngredientCallBack networkCallBack) {
        mealsRemote.getIngredients(networkCallBack);
    }

    @Override
    public void getMealById(NetworkCallBack<Meal> networkCallBack, String mealId) {
        mealsRemote.getMealById(networkCallBack, mealId);
    }

    public void getVariousRandomMeals(NetworkCallBack<Meal> networkCallBack){
        mealsRemote.getVariousRandomMeals(networkCallBack);
    }

}
