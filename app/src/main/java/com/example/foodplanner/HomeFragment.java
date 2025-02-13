package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import model.Meal;
import model.MealResponse;
import model.User;
import network.MealService;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    Meal randomMeal;
    ImageView mealIv;
    ImageView bookmarkIv;

    TextView mealNameTv;

    boolean isBookmarked = false;
    User user;
    Call<MealResponse> callRandomMeal;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        requireActivity().setTitle("Home");
        //user = HomeFragmentArgs.fromBundle(getArguments()).getUser();

        MealService mealService = RetrofitClient.getInstance().create(MealService.class);

        callRandomMeal = mealService.getRandomMeal();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: " );
        // get references to views
        bookmarkIv = view.findViewById(R.id.iv_r_bookmark);
        mealIv = view.findViewById(R.id.iv_item_thumbnail);
        mealNameTv = view.findViewById(R.id.tv_item_name);

       // populate
        callRandomMeal.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                MealResponse meals = response.body();
                List<Meal> mealsList = meals.getMeals();
                randomMeal = mealsList.get(0);
                Log.i(TAG, "onResponse: " + mealsList.get(0).getMealCountry());
                mealNameTv.setText(randomMeal.getMealName());
                Glide.with(HomeFragment.this)
                        .load(randomMeal.getMealImage())
                        .into(mealIv);
                Toast.makeText(requireContext(), "Meal Name: " + mealsList.get(0).getMealName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Toast.makeText(requireContext(), "Meal Name: " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        bookmarkIv.setOnClickListener(v -> {
            isBookmarked = !isBookmarked;

            bookmarkIv.setImageResource(isBookmarked ? R.drawable.bookmark_filled : R.drawable.bookmark_white);
        });

        user = getArguments() != null ? getArguments().getParcelable("user") : null;
        if (user == null) {
            user = new User("Guest", "guest@example.com"); // Default User
        }
        Log.i(TAG, "onViewCreated: " + user.toString());
        //Toast.makeText(requireContext(), "Authentication succeeded: " + user.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("user", user);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}