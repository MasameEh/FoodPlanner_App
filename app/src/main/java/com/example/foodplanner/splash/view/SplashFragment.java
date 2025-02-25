package com.example.foodplanner.splash.view;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSourceImp;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.splash.presenter.SplashPresenter;
import com.example.foodplanner.splash.presenter.SplashPresenterImp;
import com.example.foodplanner.utils.CustomToast;

public class SplashFragment extends Fragment implements SplashView{


    private static final String TAG = "SplashScreen";
    private static final int SPLASH_TIME_OUT = 4000;

    private SplashPresenter splashPresenter;
    private View view;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashPresenter =  new SplashPresenterImp(
                FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                        CacheHelper.getInstance(requireContext())),
                this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        splashPresenter.decideNavigationDes();
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.primary));
    }

    @Override
    public void navigateTo(int destination) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavController navController = NavHostFragment.findNavController(SplashFragment.this);
                switch (destination){
                    case SplashPresenterImp.SCREEN_HOME:
                        navController.navigate(R.id.action_splashFragment_to_homeFragment);
                        break;
                    case  SplashPresenterImp.SCREEN_WELCOME:
                        navController.navigate(R.id.action_splashFragment_to_welcomeFragment);
                        break;
                    default:
                        break;
                }
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    public void showError(String err) {
        CustomToast.showCustomErrToast(requireContext(), err);
    }
}