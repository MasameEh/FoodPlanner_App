package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.remote.network.MealsRemoteDataSource;
import com.example.foodplanner.data.remote.network.NetworkCallBack;

public class MealsRepositoryImp implements MealRepository{
    private final MealsRemoteDataSource mealsRemote;

    private static MealsRepositoryImp repo = null;

    private MealsRepositoryImp(MealsRemoteDataSource mealsRemote) {
        this.mealsRemote = mealsRemote;
    }


    public static MealsRepositoryImp getInstance(MealsRemoteDataSource mealsRemote){
        if(repo==null){
            repo = new MealsRepositoryImp(mealsRemote);
        }

        return repo;
    }
    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack){
        mealsRemote.getRandomMeal(networkCallBack);
    }

}
