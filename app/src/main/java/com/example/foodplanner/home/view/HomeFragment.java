package com.example.foodplanner.home.view;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.User;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.fav_meal_repo.MealRepositoryImp;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.home.presenter.HomePresenterImp;
import com.example.foodplanner.search_meals.view.OnMealClickListener;


public class HomeFragment extends Fragment implements HomeView, OnMealClickListener {

    private static final String TAG = "HomeFragment";

    ImageView mealIv;

    TextView mealNameTv, mealCategoryTv;

    User user;

    HomePresenter presenter;

    ProgressBar progressBar;
    RecyclerView randomMealsRv;

    CardView cv;
    private Meal randomMeal;
    RandomMealsRecyclerViewAdapter randomMealsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        requireActivity().setTitle("Home");
        //user = HomeFragmentArgs.fromBundle(getArguments()).getUser();

        presenter = new HomePresenterImp(this,
                MealRepositoryImp.getInstance(
                        MealRemoteDataSourceImp.getInstance(),
                        MealLocalDataSourceImp.getInstance(requireContext())));

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
        presenter.getVariousRandomMeals();


        cv.setOnClickListener(v ->
        {
            HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(randomMeal.getMealId());
            NavHostFragment.findNavController(this).navigate(action);

        });
        user = getArguments() != null ? getArguments().getParcelable("user") : null;
        if (user == null) {
            user = new User("Guest", "guest@example.com"); // Default User
        }
        Log.i(TAG, "onViewCreated: " + user.toString());

    }


    public void initializeUI(View view){
        mealIv = view.findViewById(R.id.iv_item_thumbnail);
        mealNameTv = view.findViewById(R.id.tv_item_name);
        mealCategoryTv = view.findViewById(R.id.tv_rand_category);

        randomMealsRv =  view.findViewById(R.id.rv_random);
        cv =  view.findViewById(R.id.cv_random_item);

        progressBar = view.findViewById(R.id.pB_progress);
    }
    @Override
    public void showRandomMealData(List<Meal> meals) {
        randomMeal = meals.get(0);
        mealNameTv.setText(randomMeal.getMealName());
        mealCategoryTv.setText(randomMeal.getMealCategory());
        Glide.with(HomeFragment.this)
                .load(randomMeal.getMealImage())
                .into(mealIv);
    }

    @Override
    public void showErr(String err) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(err);

        Toast toast = new Toast(requireContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }



    public void showRandomMealsData(List<Meal> meals){
        //progressBar.setVisibility(View.GONE);
        Log.i(TAG, "meals: " + meals.get(0).getMealName());
        randomMealsAdapter = new RandomMealsRecyclerViewAdapter(requireContext(), meals, this);
        randomMealsRv.setAdapter(randomMealsAdapter);
    }


    @Override
    public void onMealClicked(String mealId) {
        HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(randomMeal.getMealId());
        NavHostFragment.findNavController(this).navigate(action);
    }
}