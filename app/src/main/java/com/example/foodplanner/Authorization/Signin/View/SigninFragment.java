package com.example.foodplanner.Authorization.Signin.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Authorization.Signin.Presenter.SigninPresenterImpl;
import com.example.foodplanner.Authorization.Signin.Presenter.SigninPresenterInterface;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.google.android.material.textfield.TextInputEditText;

public class SigninFragment extends Fragment implements  SigninView {
    SigninPresenterInterface inPresenter = SigninPresenterImpl.getInstance(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(),FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);
    TextInputEditText email,pass;
    Button btnLogin;
    NavController navController;
    ProgressBar progressBar;
    TextView backGround;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        email = view.findViewById(R.id.editTextEmailIn);
        pass = view.findViewById(R.id.editTextPasswordIn);
        btnLogin = view.findViewById(R.id.btnLoginIn);
        backGround = view.findViewById(R.id.textView10);
        progressBar = view.findViewById(R.id.progressbarWelcome);
        btnLogin.setOnClickListener(v -> {
//            startActivity(new Intent(getActivity(), MainActivity.class));
            if(!(inPresenter.isEmail(email.getText().toString())) || (email.getText().toString().isEmpty())){
                email.setError("Wrong Email");
            }
            if(!(inPresenter.isSixOrMore(pass.getText().toString())) || (pass.getText().toString().isEmpty())) {
                pass.setError("At least 6 numbers");
            }
            inPresenter.signin(email.getText().toString(), pass.getText().toString());
            progressBar.setVisibility(View.VISIBLE);
            backGround.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void signinsuccess(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getActivity().getSharedPreferences("userdetails", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor  = sp.edit();
        editor.putString("user",inPresenter.getUid());
        editor.commit();
        navController.navigate(R.id.action_signinFragment_to_mainActivity);
        getActivity().finish();
    }

    @Override
    public void signinFailure(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        backGround.setVisibility(View.GONE);
    }
}