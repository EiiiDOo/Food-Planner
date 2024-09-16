package com.example.foodplanner.Authorization.Welcome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class WelcomeFragment extends Fragment implements WelcomeView {

    Button normalSignUp, login, guest;
    ImageButton googleSignUp;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    TextView backGround;
    GoogleSignInClient mGoogleSignInClient;
    WelcomePresenterImpl welcomePresenter;
    private final int RC_SIGN = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        welcomePresenter = new WelcomePresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(requireContext())), this);
        backGround = view.findViewById(R.id.textView10);
        progressBar = view.findViewById(R.id.progressbarWelcome);
        sp = requireActivity().getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        editor = sp.edit();
        normalSignUp = view.findViewById(R.id.btnNormalSign);
        normalSignUp.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_signUpFragment);
            //startActivity(new Intent(getActivity(), MainActivity.class));
        });
        googleSignUp = view.findViewById(R.id.btnGoogleSign);
        googleSignUp.setOnClickListener(v -> {
            signInUsingGoogle();
            progressBar.setVisibility(View.VISIBLE);
            backGround.setVisibility(View.VISIBLE);

        });
        login = view.findViewById(R.id.btnToLogin);
        login.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_signinFragment));
        guest = view.findViewById(R.id.btnguest);
        guest.setOnClickListener(v -> {
            guest.setOnClickListener(null);
            backGround.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            editor.putString("user", "guest");
            editor.commit();
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.putExtra("user", "guest");
            new Handler().postDelayed(() -> {
                startActivity(intent);
                progressBar.setVisibility(View.GONE);
                new Handler().postDelayed(() -> backGround.setVisibility(View.GONE), 500);

            }, 2000);


//            Navigation.findNavController(v).navigate(R.id.action_welcomeFragment_to_mainActivity);
            //startActivity(new Intent(getActivity(), MainActivity.class));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    void signInUsingGoogle() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken((getString(R.string.default_web_client_id)))
                .requestEmail().build();


        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), signInOptions);
        mGoogleSignInClient.signOut();

        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                welcomePresenter.signUpWithGmail(account.getIdToken());
            } catch (ApiException e) {
                onFailure(e.getMessage());
            }
        }
    }

    @Override
    public void onSuccess(String msg) {
        progressBar.setVisibility(View.INVISIBLE);
        backGround.setVisibility(View.INVISIBLE);
        if (msg.equals("Signin With Gmail Successfully"))
            navigateToMain();
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String errorMsg) {
        progressBar.setVisibility(View.INVISIBLE);
        backGround.setVisibility(View.INVISIBLE);
        Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }
    public void navigateToMain(){
        editor.putString("user", welcomePresenter.getUserid());
        editor.commit();
        Intent intent = new Intent(requireActivity(), MainActivity.class);
        intent.putExtra("user", welcomePresenter.getUserid());
        startActivity(intent);
    }
}