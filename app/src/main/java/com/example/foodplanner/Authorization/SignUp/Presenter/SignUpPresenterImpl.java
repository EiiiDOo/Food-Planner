package com.example.foodplanner.Authorization.SignUp.Presenter;

import android.util.Patterns;

import com.example.foodplanner.Authorization.SignUp.View.SignUpView;
import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Reposatery.ReposateryInterface;

public class SignUpPresenterImpl implements SignUpPresenterInterface, FireBaseCallback {
    private static SignUpPresenterImpl instance = null;
    private ReposateryInterface reposateryInterface;
    SignUpView signUpView;
    private SignUpPresenterImpl(ReposateryInterface reposateryInterface,SignUpView signUpView){
        this.reposateryInterface = reposateryInterface;
        this.signUpView = signUpView;
    }
    public static SignUpPresenterImpl getInstance(ReposateryInterface reposateryInterface,SignUpView signUpView){
        if(instance == null){
            instance = new SignUpPresenterImpl(reposateryInterface,signUpView);
        }
        return instance;
    }

    @Override
    public boolean isEmail(String mayEmail) {
            return Patterns.EMAIL_ADDRESS.matcher(mayEmail).matches();
    }

    @Override
    public boolean isConfirm(String s ,String ss) {
        return s.equals(ss);
    }

    @Override
    public void signUp(String email, String password,String confirmPassword) {
        if(isNavigate(email,password,confirmPassword))
            reposateryInterface.signUp(email, password, this);
        else
            signUpView.signUpFailure("invalid email or password");
    }

    @Override
    public void onSuccess() {
        signUpView.signUpsuccess();
    }

    @Override
    public void onFailure(String errorMsg) {
        signUpView.signUpFailure(errorMsg);
    }
    @Override
    public boolean isNavigate(String email, String pass, String confirmPass) {
        if(     (isEmail(email))&&
                !(pass.isEmpty()) &&
                !(email.isEmpty())&&
                !(confirmPass.isEmpty())&&
                isConfirm(confirmPass , pass))
            return true;
        else
            return false;
    }
}
