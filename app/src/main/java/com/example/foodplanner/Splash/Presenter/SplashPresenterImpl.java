package com.example.foodplanner.Splash.Presenter;

import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Splash.View.SplashInterface;
import com.google.firebase.auth.FirebaseUser;

public class SplashPresenterImpl implements SplashPresenter {

    ReposateryImpl reposatery;
    SplashInterface splashInterface;

    public SplashPresenterImpl(ReposateryImpl reposatery, SplashInterface splashInterface) {
        this.reposatery = reposatery;
        this.splashInterface = splashInterface;
    }


    @Override
    public String getUserId() {
        if (reposatery.getFireBaseUser() == null)
            return null;
        else
            return reposatery.getFireBaseUser().getUid();

    }


}
