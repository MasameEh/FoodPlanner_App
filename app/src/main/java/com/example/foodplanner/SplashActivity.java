package com.example.foodplanner;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import com.example.foodplanner.data.remote.network.MealService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    public static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "SplashActivity";
    private static final int SPLASH_TIME_OUT = 3800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_splash);
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_splash);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MealService mealService = retrofit.create(MealService.class);

//        Call<CategoryResponse> call = mealService.getCategories();
//        Call<MealResponse> call1 = mealService.getRandomMeal();
//        Call<MealResponse> call2 = mealService.getMealsByName("s");
//        Call<MealResponse> call3 = mealService.getMealsByFirstLetter("f");
//        Call<MealResponse> call4 = mealService.getMealsById("52772");
//
//        Call<MealResponse> call5 = mealService.getMealsByMainIngredient("chicken_breast");
//        Call<MealResponse> call6 = mealService.getMealsByCategory("Seafood");
//        Call<MealResponse> call7 = mealService.getMealsByCountry("Egyptian");

        // Delay and move to the next activity
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
//                startActivity(intent);
//                finish(); // Close this activity
//            }
//        }, SPLASH_TIME_OUT);

//        call2.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).getMealCountry());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });
//        call1.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).getMealCountry());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });
//
//        call3.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).getMealCountry());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });
//        call4.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).getMealCountry());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });


//
//        call5.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).toString());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });
//
//        call6.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).toString());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });
//
//        call7.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                MealResponse meals = response.body();
//                List<Meal> mealsList = meals.getMeals();
//                Log.i(TAG, "onResponse: " + mealsList.get(0).toString());
//
//                Toast.makeText(SplashActivity.this, "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable throwable) {
//
//            }
//        });
//        call.enqueue(new Callback<CategoryResponse>() {
//            @Override
//            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
//                if (response.isSuccessful()) {
//                    CategoryResponse products = response.body();
//                    List<Category> categoriesList = products.getCategories();
//                    Log.i(TAG, "onResponse: " + categoriesList.get(0).getCategoryName());
//                    int sizeOfList = categoriesList.size();
//                    Toast.makeText(SplashActivity.this, "Number of products: " + sizeOfList,Toast.LENGTH_SHORT).show();
//
//                } else {
//                    // Handle errors (e.g., HTTP 404, 500)
//                    Toast.makeText(SplashActivity.this, "Number of products: " + response.errorBody(),Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoryResponse> call, Throwable t) {
//                // Handle network failure or request failure
//            }
//        });
    }
}