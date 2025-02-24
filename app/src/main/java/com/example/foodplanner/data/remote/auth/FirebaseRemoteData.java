package com.example.foodplanner.data.remote.auth;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface FirebaseRemoteData {
    Single<String> login(String email, String password);

    Single<String> signUpWithEmail(String email, String password, String username);

    Single<FirebaseUser> signInWithGoogle(String idToken);

    Completable logout();

    Completable saveFavoriteToFirestore(Meal meal);
    Completable removeFromFavorites(Meal meal);


    public Single<List<Meal>> getFavoritesFromFirestore();
    FirebaseUser getCurrentUser();
}
