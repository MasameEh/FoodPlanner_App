package com.example.foodplanner.splash;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;

public class SplashFragment extends Fragment {


    private static final String TAG = "SplashScreen";
    private static final int SPLASH_TIME_OUT = 4000;
    CacheHelper cacheHelper;

    public SplashFragment() {
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
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                findNavController(view).navigate(R.id.action_splashFragment_to_welcomeFragment);
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.primary));
    }

    @Override
    public void onPause() {
        super.onPause();
        //requireActivity().getWindow().setStatusBarColor(ContextCompat.getColor(requireContext(),R.));
    }
}