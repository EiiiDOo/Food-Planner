package com.example.foodplanner.Authorization.Signin.Presenter;

public interface SigninPresenterInterface {
    boolean isEmail(String s);

    void signin(String email, String password);
    boolean isSixOrMore(String s);
    public boolean isNavigate(String email, String pass);
    String getUid();
}
