package com.example.foodplanner.welcome_screen.view;

import static androidx.navigation.Navigation.findNavController;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSource;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.welcome_screen.presenter.WelcomePresenter;
import com.example.foodplanner.welcome_screen.presenter.WelcomePresenterImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseUser;


public class WelcomeFragment extends Fragment implements WelcomeView{

    private static final int RC_SIGN_IN = 9001;
    private Button googleBtn;
    private Button emailBtn, guestBtn;

    private TextView loginTv;

    private View view;
    private GoogleSignInClient googleSignInClient;
    private WelcomePresenter presenter;

    public WelcomeFragment() {
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
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        presenter = new WelcomePresenterImp(this,
                FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSource.getInstance(),
                        CacheHelper.getInstance(requireContext())));
        initializeUI(view);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        guestBtn.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_welcomeFragment_to_homeFragment);
        });

        emailBtn.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_welcomeFragment_to_signupFragment);
        });

        googleBtn.setOnClickListener(v -> {
            signInWithGoogle();
        });


        loginTv.setOnClickListener(v -> {
            findNavController(view).navigate(R.id.action_welcomeFragment_to_loginFragment);
        });

    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    void initializeUI(View view){
        emailBtn = view.findViewById(R.id.btn_emailsignup);
        googleBtn = view.findViewById(R.id.btn_google);
        loginTv = view.findViewById(R.id.tv_login);
        guestBtn = view.findViewById(R.id.btn_guest);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                if (account != null) {
                    presenter.signInWithGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                Log.e("GoogleSignIn", "Google sign-in failed", e);
                onGoogleSignInFailure(e.getMessage());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onGoogleSignInSuccess(FirebaseUser user) {
        Toast.makeText(getContext(), "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        findNavController(view).navigate(R.id.action_welcomeFragment_to_homeFragment);
    }

    @Override
    public void onGoogleSignInFailure(String errorMessage) {
        Toast.makeText(getContext(), "Sign-in failed: " + errorMessage, Toast.LENGTH_LONG).show();
    }
}