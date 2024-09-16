package com.example.foodplanner.Authorization.Welcome;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;

public class WelcomePresenterImpl implements FireBaseCallback ,WelcomePresenter {
    private ReposateryImpl reposatery;
    private WelcomeView welcomeView;

    public WelcomePresenterImpl(ReposateryImpl reposatery, WelcomeView welcomeView) {
        this.reposatery = reposatery;
        this.welcomeView = welcomeView;
    }

    void signUpWithGmail(String idToken) {
        reposatery.signInUsingGmailAccount(idToken, this);
    }

    @Override
    public void onSuccess(String msg) {

        welcomeView.onSuccess(msg);
    }

    @Override
    public void onFailure(String errorMsg) {

        welcomeView.onFailure(errorMsg);
    }
   @Override
    public String getUserid() {
        return reposatery.getFireBaseUser().getUid();
    }
}