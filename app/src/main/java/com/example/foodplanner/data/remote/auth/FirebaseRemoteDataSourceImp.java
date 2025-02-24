package com.example.foodplanner.data.remote.auth;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class FirebaseRemoteDataSourceImp implements FirebaseRemoteData{

    private final FirebaseAuth mAuth;
    @SuppressLint("StaticFieldLeak")
    private static FirebaseRemoteDataSourceImp repo = null;

    private FirebaseRemoteDataSourceImp() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseRemoteDataSourceImp getInstance(){
        if(repo == null){
            repo = new FirebaseRemoteDataSourceImp();
        }
        return repo;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    @Override
    public Single<String> login(String email, String password) {
        return Single.create(
                emitter -> {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    emitter.onSuccess(getCurrentUser().getUid());

                                } else {
                                    emitter.onError(task.getException());

                                }
                            });
                }
        );
    }

    @Override
    public Single<String> signUpWithEmail(String email, String password, String username) {
        return Single.create(
                emitter -> {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    emitter.onSuccess(getCurrentUser().getUid());
                                    saveUserToFirestore(mAuth.getCurrentUser().getUid(), username, email);

                                } else {
                                    emitter.onError(task.getException());
                                }
                            });
                }
        );
    }

    @Override
    public Single<FirebaseUser> signInWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        return Single.create(
                emitter -> {
                    mAuth.signInWithCredential(credential)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    emitter.onSuccess(getCurrentUser());

                                } else {
                                    emitter.onError(task.getException());

                                }
                            });
                }
        );
    }

    public void saveUserToFirestore(String userId, String username, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", username);
        user.put("email", email);
        user.put("created_at", FieldValue.serverTimestamp());

        FirebaseFirestore.getInstance().collection("users")
                .document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "User added"))
                .addOnFailureListener(e -> Log.e("Firestore", "Error adding user", e));
    }

    @Override
    public Completable logout() {
        return Completable.create(
                emitter -> {
                    mAuth.signOut();
                    emitter.onComplete();
                }
            );
    }

    @Override
    public Completable saveFavoriteToFirestore(Meal meal) {
        FirebaseUser currentUser = getCurrentUser();
        if (currentUser == null) {
            return Completable.error(new Exception("User not logged in"));
        }

        return Completable.create(emitter -> {
            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(currentUser.getUid())
                    .collection("favorites")
                    .document(meal.getMealId())
                    .set(meal)
                    .addOnSuccessListener(aVoid -> {
                        Log.i("FIREBASE", "saveFavoriteToFirestore: DONE");
                        emitter.onComplete();
                    })
                    .addOnFailureListener(emitter::onError);
        });
    }

    @Override
    public Completable removeFromFavorites(Meal meal) {
        FirebaseUser currentUser = getCurrentUser();
        if (currentUser == null) {
            return Completable.error(new Exception("User not logged in"));
        }

        return Completable.create(emitter -> {
            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(currentUser.getUid())
                    .collection("favorites")
                    .document(meal.getMealId())
                    .delete()
                    .addOnSuccessListener(aVoid -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }

    @Override
    public Single<List<Meal>> getFavoritesFromFirestore() {
        FirebaseUser currentUser = getCurrentUser();
        if (currentUser == null) {
            return Single.error(new Exception("User not logged in"));
        }

        return Single.create(emitter -> {
            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(currentUser.getUid())
                    .collection("favorites")
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        List<Meal> meals = new ArrayList<>();
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            Meal meal = document.toObject(Meal.class);
                            if (meal != null) {
                                meals.add(meal);
                            }
                        }
                        emitter.onSuccess(meals);
                    })
                    .addOnFailureListener(emitter::onError);
        });
    }


}
