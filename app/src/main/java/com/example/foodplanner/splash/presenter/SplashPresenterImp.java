package com.example.foodplanner.splash.presenter;

import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.splash.view.SplashView;

public class SplashPresenterImp implements SplashPresenter{

    private final FirebaseRepository firebaseRepo;
    private final SplashView splashView;

    public static final int SCREEN_HOME = 1;
    public static final int SCREEN_WELCOME = 2;
    public SplashPresenterImp(FirebaseRepository firebaseRepo, SplashView splashView) {
        this.firebaseRepo = firebaseRepo;
        this.splashView = splashView;
    }


    @Override
    public void decideNavigationDes() {
        if(firebaseRepo.getCurrentUser() != null){
            splashView.navigateTo(SCREEN_HOME);
        }else {
            splashView.navigateTo(SCREEN_WELCOME);
        }

    }
}
