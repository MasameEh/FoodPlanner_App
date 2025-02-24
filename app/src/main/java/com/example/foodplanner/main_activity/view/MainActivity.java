package com.example.foodplanner.main_activity.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.main_activity.presenter.MainPresenter;
import com.example.foodplanner.main_activity.presenter.MainPresenterImp;
import com.example.foodplanner.utils.CustomToast;
import com.example.foodplanner.utils.InternetConnection;

import com.example.foodplanner.utils.NetworkHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements MainView{
    private static final String TAG = "MainActivity";
    private NavController navController;
    private MainPresenter presenter;
    private BottomNavigationView bottomNavigationView;
    private LottieAnimationView lottieAnimView;
    private FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeUI();

        presenter = new MainPresenterImp(
                this,
                FirebaseRepositoryImp.getInstance(
                        FirebaseRemoteDataSource.getInstance(),
                        CacheHelper.getInstance(this)
                        )
                );

        NetworkHelper.registerNetworkCallback(this);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();

        NavHostFragment navHostFr = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (navHostFr != null) {
            navController = navHostFr.getNavController();
        }

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {


            if(navDestination.getId() == R.id.splashFragment || navDestination.getId() == R.id.signupFragment
                    || navDestination.getId() == R.id.welcomeFragment || navDestination.getId() == R.id.loginFragment){
                    actionBar.hide();
                    bottomNavigationView.setVisibility(GONE);

            }else{
                bottomNavigationView.setVisibility(VISIBLE);
            }

            if(!presenter.checkGuestAccess()){
                if(navDestination.getId() == R.id.favoriteFragment
                        || navDestination.getId() == R.id.mealsPlan
                            || navDestination.getId() == R.id.profileFragment
                                || navDestination.getId() == R.id.mealDetailsFragment){
                    showSignupDialog();
                    navController.popBackStack();
                }
            }

            if (navDestination.getId() == R.id.favoriteFragment
                        || navDestination.getId() == R.id.mealsPlan) {
                showOnlineUI();
            }else{
                presenter.observeNetworkStatus();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    void initializeUI(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        lottieAnimView = findViewById(R.id.lottie_no_internet);
        lottieAnimView = findViewById(R.id.lottie_no_internet);
        fragmentContainerView = findViewById(R.id.fragmentContainerView);
    }
    public void showSignupDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Sign Up for More Features!")
                .setMessage("Create an account to save your favorite meals and meal plans across devices.")
                .setPositiveButton("Sign Up", (dialog, which) -> navController.navigate(R.id.signupFragment))
                .setNegativeButton("Continue as Guest", (dialog, which) -> dialog.dismiss())
                .show();
    }

    public void showNoNetworkAnimation() {
        lottieAnimView.setVisibility(VISIBLE);
        //lottieAnimView.setAnimation(R.raw.no_internet);

        lottieAnimView.playAnimation();
    }


    @Override
    public void showOnlineUI() {
        lottieAnimView.setVisibility(View.GONE);
        fragmentContainerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showOfflineUI() {
        showNoNetworkAnimation();
        fragmentContainerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        CustomToast.showCustomErrToast(this, error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}