package com.example.foodplanner.profile.view;

import static androidx.navigation.Navigation.findNavController;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.data.repo.meal_repo.MealRepositoryImp;
import com.example.foodplanner.profile.presenter.ProfilePresenter;
import com.example.foodplanner.profile.presenter.ProfilePresenterImp;


public class ProfileFragment extends Fragment implements ProfileView{

    Button logoutBtn;
    private static final String TAG = "ProfileFragment";
    private View view;
    private EditText username, email;
    private TextView name;
    private ProfilePresenter profilePresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().setTitle("Profile");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;

        initializeUI(view);

        profilePresenter = new ProfilePresenterImp(
                FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                CacheHelper.getInstance(requireContext())), this,
                MealRepositoryImp.getInstance(
                        FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                                CacheHelper.getInstance(requireContext())),
                        MealRemoteDataSourceImp.getInstance(),
                        MealLocalDataSourceImp.getInstance(requireContext()))
                );



        username.setText(profilePresenter.getCurrentUser().getDisplayName());
        name.setText(profilePresenter.getCurrentUser().getDisplayName());
        email.setText(profilePresenter.getCurrentUser().getEmail());

        logoutBtn.setOnClickListener(v -> {
            Log.i(TAG, "onViewCreated: log out clicked" );
            profilePresenter.logoutUser();
        });
    }

    private void initializeUI(View view){
        logoutBtn = view.findViewById(R.id.btn_logout);
        username = view.findViewById(R.id.et_fixed_username);
        email = view.findViewById(R.id.et_fixed_email);
        name = view.findViewById(R.id.tv_username);
    }

    @Override
    public void navigateToLogin() {
        findNavController(view).navigate(R.id.action_profileFragment_to_welcomeFragment);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profilePresenter.clear();
    }
}