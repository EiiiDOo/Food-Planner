package com.example.foodplanner.Authorization.SignUp.View;

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
import android.widget.Toast;

import com.example.foodplanner.Authorization.SignUp.Presenter.SignUpPresenterImpl;
import com.example.foodplanner.Authorization.SignUp.Presenter.SignUpPresenterInterface;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.google.android.material.textfield.TextInputEditText;


public class SignUpFragment extends Fragment implements SignUpView {
    NavController navController;
    SignUpPresenterInterface upPresenter = SignUpPresenterImpl.getInstance(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance()), this);
    TextInputEditText email, pass, confirmPass;
    Button signUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        email = view.findViewById(R.id.editTextEmailUp);
        pass = view.findViewById(R.id.editTextPasswordUp);
        confirmPass = view.findViewById(R.id.editTextConfirmPassword);
        signUp = view.findViewById(R.id.btnSignUpUp);
        signUp.setOnClickListener(v -> {
            if (!(upPresenter.isEmail(email.getText().toString())) || (email.getText().toString().isEmpty())) {
                email.setError("Wrong Email");
            }
            if (!(upPresenter.isConfirm(confirmPass.getText().toString(), pass.getText().toString()))) {
                confirmPass.setError("Wrong Pass");
            }
            upPresenter.signUp(email.getText().toString(), pass.getText().toString(), confirmPass.getText().toString());

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void signUpsuccess() {
        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.action_signUpFragment_to_mainActivity);
    }

    @Override
    public void signUpFailure(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }
}