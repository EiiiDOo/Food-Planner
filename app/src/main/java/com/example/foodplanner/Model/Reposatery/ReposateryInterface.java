package com.example.foodplanner.Model.Reposatery;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface ReposateryInterface {

    void fetchRandomMeals(MealsCallBack mealsCallBack);

    void fetchMealsById(String id, MealsCallBack mealsCallBack);

    void fetchMealsByCategory(String category, MealsCallBack mealsCallBack);

    void fetchMealsByCountry(String country, MealsCallBack mealsCallBack);

    void fetchMealsByIngredient(String ingredient, MealsCallBack mealsCallBack);

    void fetchMealsByName(String name, MealsCallBack mealsCallBack);

    void fetchCategories(CategoryCallback categoryCallback);

    void fetchIngredients(IngredientCallback ingredientCallback);

    void signin(String email, String password, FireBaseCallback fireBaseCallback);

    void signUp(String email, String password, FireBaseCallback fireBaseCallback);

    void signOut(FireBaseCallback fireBaseCallback);

    FirebaseUser getFireBaseUser();

    Flowable <List<Meal>> getFavMeals( );

    Completable insertFavMeals(Meal meal);

    Completable deleteFavMeals(Meal meal);

}
