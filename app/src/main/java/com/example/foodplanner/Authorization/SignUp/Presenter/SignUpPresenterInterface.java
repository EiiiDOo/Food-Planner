package com.example.foodplanner.Authorization.SignUp.Presenter;

import com.example.foodplanner.FireBase.FireBaseCallback;

public interface SignUpPresenterInterface {
    boolean isConfirm(String s, String ss);

    boolean isEmail(String s);

    void signUp(String email, String password, String confirmPassword);

    public boolean isNavigate(String email, String pass, String confirmPass);
}
