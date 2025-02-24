package com.example.foodplanner.fav_meals.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.meal_repo.MealRepositoryImp;
import com.example.foodplanner.fav_meals.presenter.FavoriteMealsPresenter;
import com.example.foodplanner.fav_meals.presenter.FavoriteMealsPresenterImp;
import com.example.foodplanner.search_meals.view.OnMealClickListener;
import com.example.foodplanner.utils.CustomToast;

import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteView, OnRemoveIconClicked<Meal>, OnMealClickListener  {



    private RecyclerView favMealRv;

    private FavoriteMealsPresenter favoritePresenter;
    private ImageView noFavMealAddedIv;
    private TextView noMealAddedTv;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoritePresenter = new FavoriteMealsPresenterImp(
                this, MealRepositoryImp.getInstance(
                    MealRemoteDataSourceImp.getInstance(),
                    MealLocalDataSourceImp.getInstance(requireContext())));

        requireActivity().setTitle("FavoriteFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeUI(view);
        favoritePresenter.getAllFavMeals();
    }

    void initializeUI(View view){
        favMealRv = view.findViewById(R.id.rv_fav_meals);
        noFavMealAddedIv = view.findViewById(R.id.iv_no_meal);
        noMealAddedTv = view.findViewById(R.id.tv_no_meal);
    }
    @Override
    public void showFavMeals(List<Meal> meals) {
        if(!meals.isEmpty()){
            noFavMealAddedIv.setVisibility(GONE);
            noMealAddedTv.setVisibility(GONE);

        }else{
            noFavMealAddedIv.setVisibility(VISIBLE);
            noMealAddedTv.setVisibility(VISIBLE);
        }
        MealRecyclerViewAdapter favAdapter =  new MealRecyclerViewAdapter(requireContext(), meals, this, this);
        favMealRv.setAdapter(favAdapter);

    }

    @Override
    public void showError(String err) {

        CustomToast.showCustomErrToast(requireContext(), err);
    }


    @Override
    public void onIconClicked(Meal meal) {
        favoritePresenter.removeFromFavoriteMeals(meal);
    }

    @Override
    public void onMealClicked(String mealId) {
        FavoriteFragmentDirections.ActionFavoriteFragmentToMealDetailsFragment action =
                FavoriteFragmentDirections.actionFavoriteFragmentToMealDetailsFragment(mealId);
        NavHostFragment.findNavController(this).navigate(action);
    }

}