package com.example.foodplanner.FireBase;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Profile.Presenter.ProfilePresenter;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public interface FireBaseRemoteDatasource {
    void signin(String email, String password, FireBaseCallback fireBaseCallback);

    void signUp(String email, String password, FireBaseCallback fireBaseCallback);

    void signOut();

    void signInUsingGmailAccount(String idToken, FireBaseCallback fireBaseCallback);

    FirebaseUser getCurrentUser();

    void backupFavouriteMeals(ArrayList<Meal> favouriteMeals, ArrayList<MealWithDay> planMeals, FireBaseCallback fireBaseCallback);

    void downloadFavouriteMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter);

    void downloadPlanMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter);
}
