package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseRepositoryImp implements FirebaseRepository {

    private final FirebaseRemoteDataSource firebaseSource;
    private final CacheHelper cacheHelper;
    private static FirebaseRepositoryImp repo;

    private FirebaseRepositoryImp(FirebaseRemoteDataSource firebaseSource, CacheHelper cacheHelper) {
        this.firebaseSource = firebaseSource;
        this.cacheHelper = cacheHelper;
    }

    public static FirebaseRepositoryImp getInstance(FirebaseRemoteDataSource firebaseSource, CacheHelper cacheHelper){
        if(repo == null){
            repo =  new FirebaseRepositoryImp(firebaseSource, cacheHelper);
        }
        return repo;
    }
    @Override
    public void saveUserId(String userId) {
        cacheHelper.saveString("userId", userId);
    }

    @Override
    public String getUserId() {
        return cacheHelper.getString("userId");
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseSource.getCurrentUser();
    }

    @Override
    public void clearUserData() {
        cacheHelper.clear();
    }
    @Override
    public void loginUser(String email, String password, AuthCallback callback) {
        firebaseSource.login(email, password, callback);
    }

    @Override
    public void registerUser(String email, String password, String username, AuthCallback callback) {
        firebaseSource.signUpWithEmail(email, password, username, callback);
    }

    @Override
    public void signInWithGoogle(String idToken, AuthCallback callback) {
        firebaseSource.signInWithGoogle(idToken, callback);
    }

    @Override
    public void logoutUser(AuthCallback callback) {
        firebaseSource.logout(callback);
    }


}
