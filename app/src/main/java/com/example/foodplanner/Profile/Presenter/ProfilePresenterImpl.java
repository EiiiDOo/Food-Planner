package com.example.foodplanner.Profile.Presenter;

import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Profile.View.ProfileInterface;

public class ProfilePresenterImpl implements ProfilePresenter {
    ReposateryImpl reposatery;
    ProfileInterface profileInterface;

    public ProfilePresenterImpl(ReposateryImpl reposatery, ProfileInterface profileInterface) {
        this.reposatery = reposatery;
        this.profileInterface = profileInterface;
        this.profileInterface.setEmail(getEmail());
    }
    public String getEmail(){return reposatery.getFireBaseUser().getEmail();}
}
