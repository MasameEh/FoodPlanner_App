package com.example.foodplanner.data.remote.auth;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class FirebaseRemoteDataSource implements AuthService{

    private final FirebaseAuth mAuth;
    @SuppressLint("StaticFieldLeak")
    private static FirebaseRemoteDataSource repo = null;

    private FirebaseRemoteDataSource() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseRemoteDataSource getInstance(){
        if(repo == null){
            repo = new FirebaseRemoteDataSource();
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
}
