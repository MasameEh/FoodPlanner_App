package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.AuthCallback;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;

public class AuthRepositoryImp implements AuthRepository {

    private FirebaseRemoteDataSource firebaseSource;
    private final CacheHelper cacheHelper;
    private static AuthRepositoryImp repo;

    private AuthRepositoryImp(FirebaseRemoteDataSource firebaseSource, CacheHelper cacheHelper) {
        this.firebaseSource = firebaseSource;
        this.cacheHelper = cacheHelper;
    }

    public static AuthRepositoryImp getInstance(FirebaseRemoteDataSource firebaseSource, CacheHelper cacheHelper){
        if(repo == null){
            repo =  new AuthRepositoryImp(firebaseSource, cacheHelper);
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
    public void logoutUser(AuthCallback callback) {

        firebaseSource.logout(callback);
    }
}
