package com.example.foodplanner.data.repo.meal_repo;

import android.util.Log;

import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSource;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSource;
import com.example.foodplanner.data.repo.FirebaseRepository;


import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepositoryImp implements MealRepository {
    private final FirebaseRepository firebaseRepo;
    private final MealRemoteDataSource mealRemote;
    private final MealLocalDataSource mealLocal;
    private static MealRepositoryImp repo = null;

    private MealRepositoryImp(FirebaseRepository firebaseRepo, MealRemoteDataSource mealsRemote, MealLocalDataSource mealLocal) {
        this.firebaseRepo = firebaseRepo;
        this.mealRemote = mealsRemote;
        this.mealLocal = mealLocal;
    }


    public static MealRepositoryImp getInstance(FirebaseRepository firebaseRepo, MealRemoteDataSource mealsRemote, MealLocalDataSource mealLocal){
        if(repo==null){
            repo = new MealRepositoryImp(firebaseRepo, mealsRemote, mealLocal);
        }

        return repo;
    }
    @Override
    public Single<List<Meal>>  getRandomMeal(){
        return mealRemote.getRandomMeal();
    }

    @Override
    public Single<List<Meal>> getCountries() {
        return mealRemote.getCountries();
    }

    @Override
    public Single<List<Ingredient>>  getIngredients() {
        return mealRemote.getIngredients();
    }

    @Override
    public Single<List<Meal>> getMealById(String mealId) {
        return mealRemote.getMealById( mealId);
    }

    @Override
    public Single<List<Meal>> getMealsByCategory(String category) {
        return mealRemote.getMealsByCategory(category);
    }

    @Override
    public Single<List<Meal>> getMealsByIngredient(String ingredient) {
        return mealRemote.getMealsByIngredient(ingredient);
    }

    @Override
    public Single<List<Meal>> getMealsByCountry(String country) {
        return mealRemote.getMealsByCountry(country);
    }


    public Single<List<Meal>> getVariousRandomMeals(){
        return mealRemote.getVariousRandomMeals();
    }

    //Local database
    @Override
    public Flowable<List<Meal>> getFavStoredMeals() {
        return mealLocal.getFavStoredMeals();
    }

    @Override
    public Completable insertAllMeals(List<Meal> meals) {
        Log.i("logggin", "Repository: Starting to insert meals: " + meals);
        return mealLocal.insertAllMeals(meals)
                .doOnComplete(() -> Log.i("logggin", "Repository: Meals inserted successfully"))
                .doOnError(error -> Log.e("logggin", "Repository: Error inserting meals", error));
    }

    @Override
    public Completable insertMeal(Meal meal){
        return mealLocal.insert(meal).andThen(
                firebaseRepo.getCurrentUser() != null ?
                        firebaseRepo.saveFavoriteToFirestore(meal) :
                        Completable.complete()
        );
    }
    @Override
    public Completable deleteMeal(Meal meal){
        return mealLocal.delete(meal) .andThen(
                firebaseRepo.getCurrentUser() != null ?
                        firebaseRepo.removeFromFavorites(meal) :
                        Completable.complete()
        );
    }

    @Override
    public Completable clearDatabase() {
        return mealLocal.clearDatabase();
    }

}
