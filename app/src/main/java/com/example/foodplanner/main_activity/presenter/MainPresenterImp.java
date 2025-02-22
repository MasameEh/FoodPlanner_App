package com.example.foodplanner.main_activity.presenter;



import com.example.foodplanner.data.repo.FirebaseRepository;
import com.example.foodplanner.main_activity.view.MainView;
import com.example.foodplanner.utils.NetworkHelper;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenterImp implements MainPresenter{

    private final MainView mainView;
    private final FirebaseRepository firebaseRepo;
    private static final String TAG = "MainPresenterImp";

    public MainPresenterImp(MainView view, FirebaseRepository firebaseRepo) {
        this.mainView = view;
        this.firebaseRepo = firebaseRepo;
    }

    @Override
    public boolean checkGuestAccess() {
        return firebaseRepo.getCurrentUser() != null;
    }

    public void observeNetworkStatus() {
        Disposable disposable = NetworkHelper.getNetworkStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> {
                    if (isConnected) {
                        mainView.showOnlineUI();
                    } else {
                        mainView.showOfflineUI();
                    }
                });
    }
}

