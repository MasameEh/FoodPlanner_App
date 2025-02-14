package com.example.foodplanner.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheHelper {
    private static final String PREF_NAME = "food_planner_prefs";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static CacheHelper cacheHelper;
    private CacheHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static CacheHelper getInstance(Context context){
        if(cacheHelper == null){
            cacheHelper = new CacheHelper(context);
        }
        return cacheHelper;
    }
    // Save string value
    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    // Get string value
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    // Save boolean value
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    // Get boolean value
    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    // Remove a specific key
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    // Clear all data
    public void clear() {
        editor.clear();
        editor.commit();
    }
}
