package com.example.foodplanner.Authorization.Signin.Presenter;

import android.util.Patterns;

import com.example.foodplanner.Authorization.Signin.View.SigninView;
import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Reposatery.ReposateryInterface;

public class SigninPresenterImpl implements SigninPresenterInterface, FireBaseCallback {
    private static SigninPresenterImpl instance = null;
    private SigninView signinView;
    private ReposateryInterface reposateryInterface;

    private SigninPresenterImpl(ReposateryInterface reposateryInterface, SigninView signinView) {
        this.reposateryInterface = reposateryInterface;
        this.signinView = signinView;
    }

    public static SigninPresenterImpl getInstance(ReposateryInterface reposateryInterface, SigninView signinView) {
        if (instance == null) {
            instance = new SigninPresenterImpl(reposateryInterface, signinView);
        }
        return instance;
    }

    @Override
    public boolean isEmail(String mayEmail) {
        return Patterns.EMAIL_ADDRESS.matcher(mayEmail).matches();
    }

    @Override
    public void signin(String email, String password) {
        if(isNavigate(email,password))
            reposateryInterface.signin(email, password, this);
        else
            signinView.signinFailure("invalid email or password");
    }

    @Override
    public boolean isSixOrMore(String s) {
        return (s.length() >= 6);
    }

    @Override
    public boolean isNavigate(String email, String pass) {
        if(     (isEmail(email))&&
                !(pass.isEmpty()) &&
                !(email.isEmpty()))
            return true;
        else
            return false;
    }

    @Override
    public String getUid() {
        return reposateryInterface.getFireBaseUser().getUid();
    }

    @Override
    public void onSuccess() {
        signinView.signinsuccess();
    }

    @Override
    public void onFailure(String errorMsg) {
        signinView.signinFailure(errorMsg);
    }
}
