package com.example.foodplanner.Model.Reposatery;


import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.MealsResponse;
import com.example.foodplanner.Profile.Presenter.ProfilePresenter;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface ReposateryInterface {

    Observable<MealsResponse> fetchRandomMeals();

    Observable<MealsResponse> fetchMealsById(String id);

    Observable<MealsResponse> fetchMealsByCategory(String category);

    Observable<MealsResponse> fetchMealsByCountry(String country);

    Observable<MealsResponse> fetchMealsByIngredient(String ingredient);

    Observable<MealsResponse> fetchMealsByName(String name);

    Observable<Categoryresponse> fetchCategories();

    Observable<IngredientResponse> fetchIngredients();

    void signin(String email, String password, FireBaseCallback fireBaseCallback);

    void signUp(String email, String password, FireBaseCallback fireBaseCallback);

    void signOut();

    void signInUsingGmailAccount(String idToken, FireBaseCallback fireBaseCallback);

    FirebaseUser getFireBaseUser();

    Flowable<List<Meal>> getFavMeals(String id);

    Completable insertFavMeals(Meal meal);

    Completable deleteFavMeals(Meal meal);

    Flowable<List<MealWithDay>> getMealPlan(String userId, String day);

    Flowable<List<MealWithDay>> getMealPlan(String userId);

    Completable deleteMealPlan(MealWithDay mealWithDay);

    Completable insertMealPlan(MealWithDay mealWithDay);

    void backupAllMeals(ArrayList<Meal> favouriteMeals, ArrayList<MealWithDay> mealPlan, FireBaseCallback fireBaseCallback);

    void downloadFavouriteMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter);

    void downloadPlanMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter);

    Completable onSuccessDownloadFavouriteMeals(List<Meal> favouriteMeals);

    Completable onSuccessDownloadPlanMeals(List<MealWithDay> favouriteMeals);


}
