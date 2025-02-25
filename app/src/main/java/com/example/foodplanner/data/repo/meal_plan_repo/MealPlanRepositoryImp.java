package com.example.foodplanner.data.repo.meal_plan_repo;

import android.util.Log;

import com.example.foodplanner.data.local.db.MealPlan.MealPlanLocalDataSource;
import com.example.foodplanner.data.model.MealPlan;
import com.example.foodplanner.data.repo.FirebaseRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealPlanRepositoryImp implements MealPlanRepository{


    private final MealPlanLocalDataSource mealPlanLocal;
    private static MealPlanRepositoryImp repo = null;

    private final FirebaseRepository firebaseRepo;;

    private MealPlanRepositoryImp(MealPlanLocalDataSource mealPlanLocal, FirebaseRepository firebaseRepo) {
        this.mealPlanLocal = mealPlanLocal;
        this.firebaseRepo = firebaseRepo;
    }

    public static MealPlanRepositoryImp getInstance(MealPlanLocalDataSource mealLocal, FirebaseRepository firebaseRepo){
        if(repo==null){
            repo = new MealPlanRepositoryImp(mealLocal, firebaseRepo);
        }

        return repo;
    }

    @Override
    public Flowable<List<MealPlan>> getMealsForDay(long selectedDate) {
        return mealPlanLocal.getMealsForDay(selectedDate);
    }

    @Override
    public Flowable<List<MealPlan>> getMealsForWeek(long startDate, long endDate) {
        return mealPlanLocal.getMealsForWeek(startDate, endDate);
    }

    @Override
    public Single<List<MealPlan>> getAllPlannedMeals() {
        return mealPlanLocal.getAllPlannedMeals();
    }

    @Override
    public Completable insertAllPlannedMeals(List<MealPlan> mealPlans) {
        return mealPlanLocal.insertAllPlannedMeals(mealPlans)
                .doOnComplete(() -> Log.i("planned meals", "Repository: MealPlans inserted successfully"))
                .doOnError(error -> Log.e("planned meals", "Repository: Error inserting MealPlans", error));
    }

    @Override
    public Completable insertMealPlan(MealPlan mealPlan) {
        return mealPlanLocal.insertMealPlan(mealPlan).andThen(
                firebaseRepo.getCurrentUser() != null ?
                        firebaseRepo.savePlanToFirestore(mealPlan):
                        Completable.complete()
        );
    }

    @Override
    public Completable deleteMealPlan(MealPlan mealPlan) {
        return mealPlanLocal.deleteMealPlan(mealPlan).andThen(
                firebaseRepo.getCurrentUser() != null ?
                        firebaseRepo.removeFromPlans(mealPlan):
                        Completable.complete()
        );
    }
}
