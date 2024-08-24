package com.example.foodplanner.Model.Reposatery;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.RepoRoom.Room.MealsFavLocalDataSource;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.Network.Base.RemoteDataSource;
import com.example.foodplanner.Network.CategoryCallback;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

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
    public void fetchRandomMeals(MealsCallBack mealsCallBack) {
        remoteDataSource.makeRandomMealsCall(mealsCallBack);
    }

    @Override
    public void fetchMealsById(String id, MealsCallBack mealsCallBack) {
        remoteDataSource.makeMealsByIdCall(mealsCallBack, id);
    }

    @Override
    public void fetchMealsByCategory(String category, MealsCallBack mealsCallBack) {
        remoteDataSource.makeMealsByCategoryCall(mealsCallBack, category);
    }

    @Override
    public void fetchMealsByCountry(String country, MealsCallBack mealsCallBack) {
        remoteDataSource.makeMealsByCountryCall(mealsCallBack, country);
    }

    @Override
    public void fetchMealsByIngredient(String ingredient, MealsCallBack mealsCallBack) {
        remoteDataSource.makeMealsByIngredientCall(mealsCallBack, ingredient);
    }

    @Override
    public void fetchMealsByName(String name, MealsCallBack mealsCallBack) {
        remoteDataSource.makeMealByNameCall(mealsCallBack, name);
    }

    @Override
    public void fetchCategories(CategoryCallback networkCallback) {
        remoteDataSource.makeCategoryCall(networkCallback);
    }

    @Override
    public void fetchIngredients(IngredientCallback callback) {
        remoteDataSource.makeIngredientsCall(callback);
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
    public void signOut(FireBaseCallback fireBaseCallback) {
        fireBase.signOut(fireBaseCallback);
    }

    @Override
    public FirebaseUser getFireBaseUser() {
        return fireBase.getCurrentUser();
    }

    @Override
    public Flowable<List<Meal>> getFavMeals() {
        return localDataSource.getMealsThatMatchid();
    }

    @Override
    public Completable insertFavMeals(Meal meal) {
        return localDataSource.insertMealFav(meal);
    }

    @Override
    public Completable deleteFavMeals(Meal meal) {
        return localDataSource.deleteMealFav(meal);
    }


}
