package com.example.foodplanner.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.core.Observable;

public class NetworkHelper {
    private static final BehaviorSubject<Boolean> networkStatusSubject = BehaviorSubject.createDefault(false);

    public static Observable<Boolean> getNetworkStatus() {
        return networkStatusSubject.hide();
    }

    public static void registerNetworkCallback(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                networkStatusSubject.onNext(true);
            }

            @Override
            public void onLost(Network network) {
                networkStatusSubject.onNext(false);
            }
        });
    }
}
