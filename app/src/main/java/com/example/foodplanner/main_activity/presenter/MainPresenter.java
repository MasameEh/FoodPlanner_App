package com.example.foodplanner.main_activity.presenter;

import io.reactivex.rxjava3.disposables.Disposable;

public interface MainPresenter {
    void observeNetworkStatus();
    boolean checkGuestAccess();
}
