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
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void login(String email, String password, AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(mAuth.getCurrentUser());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void signUpWithEmail(String email, String password, String username, AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserToFirestore(mAuth.getCurrentUser().getUid(), username, email);
                        callback.onSuccess(mAuth.getCurrentUser());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void signInWithGoogle(String idToken, AuthCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(mAuth.getCurrentUser());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
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

//    public void getUserFromFirestore(String userId, UserCallback callback) {
//        FirebaseFirestore.getInstance().collection("users")
//                .document(userId)
//                .get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    if (documentSnapshot.exists()) {
//                        String name = documentSnapshot.getString("name");
//                        String email = documentSnapshot.getString("email");
//                        callback.onSuccess(new User(userId, name, email));
//                    } else {
//                        callback.onFailure("User not found");
//                    }
//                })
//                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
//    }

    @Override
    public void logout(AuthCallback callback) {
        try {
            mAuth.signOut();
            callback.onSuccess(mAuth.getCurrentUser());
        } catch (Exception e) {
            callback.onFailure(e);
        }

    }
}
