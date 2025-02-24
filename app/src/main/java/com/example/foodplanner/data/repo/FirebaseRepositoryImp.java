package com.example.foodplanner.data.repo;

import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

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
    public Single<String> loginUser(String email, String password) {
        return firebaseSource.login(email, password);
    }

    @Override
    public Single<String> registerUser(String email, String password, String username) {
        return firebaseSource.signUpWithEmail(email, password, username);
    }

    @Override
    public Single<FirebaseUser> signInWithGoogle(String idToken) {
        return firebaseSource.signInWithGoogle(idToken);
    }

    @Override
    public Completable logoutUser() {
        return firebaseSource.logout();
    }


}
