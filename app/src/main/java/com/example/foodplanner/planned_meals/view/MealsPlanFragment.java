package com.example.foodplanner.planned_meals.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.local.db.MealPlan.MealPlanLocalDataSourceImp;
import com.example.foodplanner.data.model.MealPlan;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSourceImp;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.data.repo.meal_plan_repo.MealPlanRepositoryImp;
import com.example.foodplanner.fav_meals.view.FavoriteFragmentDirections;
import com.example.foodplanner.fav_meals.view.OnRemoveIconClicked;
import com.example.foodplanner.planned_meals.presenter.MealsPlanPresenter;
import com.example.foodplanner.planned_meals.presenter.MealsPlanPresenterImp;
import com.example.foodplanner.search_meals.view.OnMealClickListener;
import com.example.foodplanner.utils.CustomToast;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public class MealsPlanFragment extends Fragment implements MealsPlanView,
        OnRemoveIconClicked<MealPlan>, OnMealClickListener {


    private static final String TAG = "MealsPlanFragment";

    private RecyclerView mealPlanRv;
    private CalendarView calendarView;
    private TextView dateTv, planTv;
    private ImageView pandaIv;
    private MealsPlanPresenter mealsPresenter;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealsPresenter = new MealsPlanPresenterImp(
                MealPlanRepositoryImp.getInstance(
                        MealPlanLocalDataSourceImp.getInstance(requireContext()),
                        FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                                CacheHelper.getInstance(requireContext()))
                ), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);

        Calendar currentDateCal = Calendar.getInstance();

        currentDateCal.set(Calendar.HOUR_OF_DAY, 0);
        currentDateCal.set(Calendar.MINUTE, 0);
        currentDateCal.set(Calendar.SECOND, 0);
        currentDateCal.set(Calendar.MILLISECOND, 0);
        long currentDateMillis = currentDateCal.getTimeInMillis();

        Disposable disposable1 = mealsPresenter.getMealsForDay(currentDateMillis);
        compositeDisposable.add(disposable1);

        calendarView.setMinDate(Calendar.getInstance().getTimeInMillis());
        calendarView.setOnDateChangeListener(
                (view1, year, month, dayOfMonth) -> {
                    Calendar selectedDateCal = Calendar.getInstance();
                    selectedDateCal.set(year, month, dayOfMonth, 0, 0, 0);

                    // Ensure milliseconds are zero
                    selectedDateCal.set(Calendar.MILLISECOND, 0);
                    //It returns the number of milliseconds since January 1, 1970
                    long selectedDateMillis = selectedDateCal.getTimeInMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault());

                    String date = sdf.format(new Date(selectedDateMillis));

                    Log.i(TAG, "calendarView: --- " + date);
                    dateTv.setText(String.format("%s%s", getString(R.string.planned_meals_for), date));
                    Disposable disposable2 = mealsPresenter.getMealsForDay(selectedDateMillis);
                    compositeDisposable.add(disposable2);

                }
        );
    }


    private void initializeUI(View view){
        calendarView = view.findViewById(R.id.calendarView_meals);
        mealPlanRv = view.findViewById(R.id.rv_planned_meals);
        dateTv = view.findViewById(R.id.tv_day);
        planTv = view.findViewById(R.id.tv__no_plan);
        pandaIv = view.findViewById(R.id.iv_panda);
    }
    @Override
    public void showMealForDay(List<MealPlan> meals) {
        Log.i(TAG, "showMealForDay: --- " + meals.toString());

        if(!meals.isEmpty()){
            planTv.setVisibility(GONE);
            pandaIv.setVisibility(GONE);

        }else{
            planTv.setVisibility(VISIBLE);
            pandaIv.setVisibility(VISIBLE);
        }
        MealPlanRecyclerViewAdapter mealAdapter = new MealPlanRecyclerViewAdapter(requireContext(), meals, this, this);
        mealPlanRv.setAdapter(mealAdapter);
    }

    @Override
    public void showError(String err) {
        CustomToast.showCustomErrToast(requireContext(), err);
    }

    @Override
    public void onIconClicked(MealPlan meal) {
        Disposable disposable = mealsPresenter.removeMealsForDay(meal);
        compositeDisposable.add(disposable);
    }

    @Override
    public void onMealClicked(String mealId) {
        MealsPlanFragmentDirections.ActionMealsPlanToMealDetailsFragment action =
                MealsPlanFragmentDirections.actionMealsPlanToMealDetailsFragment(mealId);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}