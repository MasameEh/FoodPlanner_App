package com.example.foodplanner;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.data.local.CacheHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    NavController navController;
    NavigationView navigationView;

    CacheHelper cacheHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        cacheHelper =  new CacheHelper(this);
//        String userId = cacheHelper.getString("userId");

        //navigationView = findViewById(R.id.nav_view);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary));
//        window.getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        window.setStatusBarColor(Color.TRANSPARENT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(R.drawable.home);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        NavHostFragment navHostFr = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (navHostFr != null) {
            navController = navHostFr.getNavController();
        }
        //NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if(navDestination.getId() == R.id.splashFragment || navDestination.getId() == R.id.signupFragment
                    || navDestination.getId() == R.id.welcomeFragment || navDestination.getId() == R.id.loginFragment){
                    actionBar.hide();
                    bottomNavigationView.setVisibility(View.GONE);

            }else{
                bottomNavigationView.setVisibility(View.VISIBLE);
                actionBar.show();
            }
        });

//        if(userId != null && navController.getCurrentDestination() != null){
//            navController.navigate(R.id.action_splashFragment_to_homeFragment);
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}