package com.example.foodplanner.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InternetConnection {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

//    public @NonNull Single<Boolean> hasActiveInternetConnection(Context context) {
//        return Single.create(emitter -> {
//            if (!isNetworkAvailable(context)) {
//                emitter.onSuccess(false);
//                return;
//            }
//
//            try {
//                HttpURLConnection urlConn = (HttpURLConnection) (new URL("https://www.google.com/").openConnection());
//                urlConn.setRequestProperty("User-Agent", "Android");
//                urlConn.setRequestProperty("Connection", "close");
//                urlConn.setConnectTimeout(1500);
//                urlConn.connect();
//
//                boolean isConnected = (urlConn.getResponseCode() == 200);
//                emitter.onSuccess(isConnected);
//            } catch (IOException e) {
//                emitter.onError(e);
//            }
//        }).subscribeOn(Schedulers.io())  // Run on background thread
//                .observeOn(AndroidSchedulers.mainThread()).onErrorReturnItem(false);
//    }

}
