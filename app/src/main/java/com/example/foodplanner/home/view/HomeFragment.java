package com.example.foodplanner.home.view;

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

import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealResponse;
import com.example.foodplanner.data.model.User;
import com.example.foodplanner.data.remote.network.MealService;
import com.example.foodplanner.data.remote.network.MealsRemoteDataSource;
import com.example.foodplanner.data.repo.MealsRepositoryImp;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.home.presenter.HomePresenterImp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements HomeView{

    private static final String TAG = "HomeFragment";

    ImageView mealIv;
    ImageView bookmarkIv;

    TextView mealNameTv;

    boolean isBookmarked = false;
    User user;

    HomePresenter presenter;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        requireActivity().setTitle("Home");
        //user = HomeFragmentArgs.fromBundle(getArguments()).getUser();

        presenter = new HomePresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSource.getInstance()));

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
        initializeUI(view);
        presenter.getRandomMeal();

        bookmarkIv.setOnClickListener(v -> {
            presenter.toggleBookmark();
        });

        user = getArguments() != null ? getArguments().getParcelable("user") : null;
        if (user == null) {
            user = new User("Guest", "guest@example.com"); // Default User
        }
        Log.i(TAG, "onViewCreated: " + user.toString());
        //Toast.makeText(requireContext(), "Authentication succeeded: " + user.toString(), Toast.LENGTH_SHORT).show();
    }


    public void initializeUI(View view){
        bookmarkIv = view.findViewById(R.id.iv_r_bookmark);
        mealIv = view.findViewById(R.id.iv_item_thumbnail);
        mealNameTv = view.findViewById(R.id.tv_item_name);
    }
    @Override
    public void showRandomMealData(List<Meal> meals) {
        Meal randomMeal = meals.get(0);
        mealNameTv.setText(randomMeal.getMealName());
        Glide.with(HomeFragment.this)
                .load(randomMeal.getMealImage())
                .into(mealIv);
    }

    @Override
    public void showErr(String err) {
        Toast.makeText(requireContext(), "Meal Name: " + err,Toast.LENGTH_SHORT).show();
    }

    public void updateBookmarkIcon(boolean isBookmarked){
        bookmarkIv.setImageResource(isBookmarked ? R.drawable.bookmark_filled : R.drawable.bookmark_white);
        Toast.makeText(requireContext(), "Added to favorite",Toast.LENGTH_SHORT).show();
    }
}