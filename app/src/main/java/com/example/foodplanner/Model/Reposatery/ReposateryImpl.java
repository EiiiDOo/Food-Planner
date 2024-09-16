package com.example.foodplanner.Model.Reposatery;



import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasource;
import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.MealsResponse;
import com.example.foodplanner.Model.RepoRoom.Room.MealsFavLocalDataSource;
import com.example.foodplanner.Network.Base.RemoteDataSource;
import com.example.foodplanner.Profile.Presenter.ProfilePresenter;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class ReposateryImpl implements ReposateryInterface {
    private static ReposateryImpl instance = null;
    private RemoteDataSource remoteDataSource;
    FireBaseRemoteDatasource fireBase;
    MealsFavLocalDataSource localDataSource;

    private ReposateryImpl(RemoteDataSource remoteDataSource, FireBaseRemoteDatasource fireBase, MealsFavLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.fireBase = fireBase;
        this.localDataSource = localDataSource;
    }

    public static ReposateryImpl getInstance(RemoteDataSource remoteDataSource, FireBaseRemoteDatasource fireBase, MealsFavLocalDataSource localDataSource) {
        if (instance == null) {
            instance = new ReposateryImpl(remoteDataSource, fireBase,localDataSource);

        }
        return instance;
    }


    @Override
    public Observable<MealsResponse> fetchRandomMeals() {
        return remoteDataSource.makeRandomMealsCall();
    }

    @Override
    public Observable<MealsResponse> fetchMealsById(String id) {
        return remoteDataSource.makeMealsByIdCall(id);
    }

    @Override
    public Observable<MealsResponse> fetchMealsByCategory(String category) {
        return remoteDataSource.makeMealsByCategoryCall(category);
    }

    @Override
    public Observable<MealsResponse> fetchMealsByCountry(String country) {
        return remoteDataSource.makeMealsByCountryCall(country);
    }

    @Override
    public Observable<MealsResponse> fetchMealsByIngredient(String ingredient) {
        return remoteDataSource.makeMealsByIngredientCall(ingredient);
    }

    @Override
    public Observable<MealsResponse> fetchMealsByName(String name) {
        return remoteDataSource.makeMealByNameCall(name);
    }

    @Override
    public Observable<Categoryresponse> fetchCategories() {
        return remoteDataSource.makeCategoryCall();
    }

    @Override
    public Observable<IngredientResponse> fetchIngredients() {
        return remoteDataSource.makeIngredientsCall();
    }

    @Override
    public void signin(String email, String password, FireBaseCallback fireBaseCallback) {
        fireBase.signin(email, password, fireBaseCallback);
    }

    @Override
    public void signUp(String email, String password, FireBaseCallback fireBaseCallback) {
        fireBase.signUp(email, password, fireBaseCallback);
    }

    @Override
    public void signOut() {
        fireBase.signOut();
    }

    @Override
    public void signInUsingGmailAccount(String idToken, FireBaseCallback fireBaseCallback) {
        fireBase.signInUsingGmailAccount(idToken, fireBaseCallback);
    }

    @Override
    public FirebaseUser getFireBaseUser() {
        return fireBase.getCurrentUser();
    }

    @Override
    public Flowable<List<Meal>> getFavMeals(String id) {
        return localDataSource.getMealsThatMatchid(id);
    }

    @Override
    public Completable insertFavMeals(Meal meal) {
        return localDataSource.insertMealFav(meal);
    }


    @Override
    public Completable deleteFavMeals(Meal meal) {
        return localDataSource.deleteMealFav(meal);
    }

    @Override
    public Flowable<List<MealWithDay>> getMealPlan(String userId, String day) {
        return localDataSource.getMealPlan(userId,day);
    }
    @Override
    public Flowable<List<MealWithDay>> getMealPlan(String userId) {
        return localDataSource.getMealPlan(userId);
    }

    @Override
    public Completable deleteMealPlan(MealWithDay mealWithDay) {
        return localDataSource.deleteMealPlan(mealWithDay);
    }

    @Override
    public Completable insertMealPlan(MealWithDay mealWithDay) {
        return localDataSource.insertMealPlan(mealWithDay);
    }

    @Override
    public void backupAllMeals(ArrayList<Meal> favouriteMeals, ArrayList<MealWithDay> mealPlan, FireBaseCallback fireBaseCallback) {
        fireBase.backupFavouriteMeals(favouriteMeals,mealPlan, fireBaseCallback);
    }

    @Override
    public void downloadFavouriteMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter) {
        fireBase.downloadFavouriteMeals(fireBaseCallback,profilePresenter);
    }

    @Override
    public void downloadPlanMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter) {
        fireBase.downloadPlanMeals(fireBaseCallback,profilePresenter);
    }

    @Override
    public Completable onSuccessDownloadFavouriteMeals(List<Meal> favouriteMeals) {
        return localDataSource.insertMealFav(favouriteMeals);
    }

    @Override
    public Completable onSuccessDownloadPlanMeals(List<MealWithDay> favouriteMeals) {
        return localDataSource.insertMealPlan(favouriteMeals);
    }


}
