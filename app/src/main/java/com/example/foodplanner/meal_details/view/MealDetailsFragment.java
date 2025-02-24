package com.example.foodplanner.meal_details.view;


import static com.example.foodplanner.utils.CustomToast.showCustomDoneToast;
import static com.example.foodplanner.utils.CustomToast.showCustomErrToast;

import android.app.DatePickerDialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.local.db.MealPlan.MealPlanLocalDataSourceImp;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealPlan;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.meal_plan_repo.MealPlanRepositoryImp;
import com.example.foodplanner.data.repo.meal_repo.MealRepositoryImp;
import com.example.foodplanner.meal_details.presenter.MealDetailsPresenter;
import com.example.foodplanner.meal_details.presenter.MealDetailsPresenterImp;
import com.example.foodplanner.utils.CountryMapper;
import com.example.foodplanner.utils.MealUtils;


import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


public class MealDetailsFragment extends Fragment implements MealDetailsView{

    private static final String TAG = "MealDetailsFragment";

    private MealDetailsPresenter presenter;
    private TextView mealName, mealCountry, mealCategory;
    private ImageView mealImage, countryImage, favIcon, calenderIcon;

    private Meal meal;
    private MealPlan mealPlan;

    private WebView webView;

    private TextView instructionsTv;

    private RecyclerView ingredientsRv;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MealDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);
        String mealId = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealId();

        presenter = new MealDetailsPresenterImp(
                MealRepositoryImp.getInstance(
                        MealRemoteDataSourceImp.getInstance(),
                        MealLocalDataSourceImp.getInstance(requireContext())),
                MealPlanRepositoryImp.getInstance(
                        MealPlanLocalDataSourceImp.getInstance(requireContext())
                ),
                this);

        presenter.getMealDetails(mealId);

        favIcon.setOnClickListener(v -> {
            presenter.toggleFavIcon();

        });
        calenderIcon.setOnClickListener(
                v -> {
                    showDatePicker();
                });
    }


    public void initializeUI(View view){
        mealName = view.findViewById(R.id.tv_meal_name);
        mealCountry = view.findViewById(R.id.tv_meal_country);
        mealImage = view.findViewById(R.id.iv_meal_detail_image);
        ingredientsRv = view.findViewById(R.id.rv_meal_ingredients);
        instructionsTv = view.findViewById(R.id.tv_instructions);
        webView = view.findViewById(R.id.web_view);
        mealCategory = view.findViewById(R.id.tv_category);
        countryImage = view.findViewById(R.id.iv_meal_country);
        favIcon = view.findViewById(R.id.iv_favorite);
        calenderIcon = view.findViewById(R.id.iv_calender);

    }
    @Override
    public void showMealDetails(List<Meal> meals) {

        meal = meals.get(0);
        mealPlan = new MealPlan();
        mealPlan.setMealId(meal.getMealId());
        mealPlan.setMealName(meal.getMealName());
        mealPlan.setMealImage(meal.getMealImage());


        List<Ingredient> ingredientsList = MealUtils.getIngredientsList(meal);
        Log.i(TAG, "showMealDetails: " + ingredientsList + meal.getStrIngredient1());
        mealCountry.setText(meal.getMealCountry());
        mealName.setText(meal.getMealName());
        instructionsTv.setText(meal.getMealInstructions());
        countryImage.setImageResource(CountryMapper.getImageForCountry(meal.getMealCountry()));
        mealCategory.setText(meal.getMealCategory());


        // Load the video properly into WebView
        String videoUrl = meal.getMealVideo();

        if (videoUrl != null && videoUrl.contains("watch?v=")) {
            String videoId = videoUrl.split("watch\\?v=")[1]; // Extract video ID
            String embedUrl = "<html><body style='margin:0;padding:0;'><iframe width='100%' height='100%' src='https://www.youtube.com/embed/"
                    + videoId + "' frameborder='0' allowfullscreen></iframe></body></html>";
            webView.loadData(embedUrl, "text/html", "utf-8");
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        Log.i(TAG, "showMealDetails: " + meal.getMealInstructions());
        Glide.with(requireContext()).load(meal.getMealImage()).into(mealImage);

        IngredientsRecyclerViewAdapter adapter = new IngredientsRecyclerViewAdapter(requireContext(), ingredientsList);
        ingredientsRv.setAdapter(adapter);
    }
    @Override
    public void updateToggleIcon(boolean isClicked){
        favIcon.setImageResource(isClicked ? R.drawable.touch_colored: R.drawable.touch);
        if(isClicked){
            Disposable disposable = presenter.addToFavoriteMeals(meal);
            compositeDisposable.add(disposable);
        }else{
            Disposable disposable = presenter.removeFromFavoriteMeals(meal);
            compositeDisposable.add(disposable);
        }
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),R.style.DialogTheme,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedDateCal = Calendar.getInstance();
                    selectedDateCal.set(selectedYear, selectedMonth, selectedDay, 0, 0, 0);

                    // Ensure milliseconds are zero
                    selectedDateCal.set(Calendar.MILLISECOND, 0);

                    //It returns the number of milliseconds since January 1, 1970
                    long selectedDateMillis = selectedDateCal.getTimeInMillis();

                    mealPlan.setDate(selectedDateMillis);
                    Log.i(TAG, "showPlanMealDetails: " + mealPlan.toString());
                    Disposable disposable = presenter.addMealToCalender(mealPlan);
                    compositeDisposable.add(disposable);

                }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.primary));
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.primary));
    }
    @Override
    public void showToast(String msg) {
        showCustomDoneToast(requireContext(), msg);
        //Toast.makeText(requireContext(), msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String err) {
        showCustomErrToast(requireContext(), err);
    }


    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}