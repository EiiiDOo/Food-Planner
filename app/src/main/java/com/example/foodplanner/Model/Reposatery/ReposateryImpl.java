package com.example.foodplanner.Model.Reposatery;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasource;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.Network.Base.RemoteDataSource;
import com.example.foodplanner.Network.CategoryCallback;

public class ReposateryImpl implements ReposateryInterface {
    private static ReposateryImpl instance = null;
    private RemoteDataSource remoteDataSource;
    FireBaseRemoteDatasource fireBase;

    private ReposateryImpl(RemoteDataSource remoteDataSource, FireBaseRemoteDatasource fireBase) {
        this.remoteDataSource = remoteDataSource;
        this.fireBase = fireBase;
    }

    public static ReposateryImpl getInstance(RemoteDataSource remoteDataSource, FireBaseRemoteDatasource fireBase) {
        if (instance == null) {
            instance = new ReposateryImpl(remoteDataSource, fireBase);

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


}
