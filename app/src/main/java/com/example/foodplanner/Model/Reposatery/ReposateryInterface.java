package com.example.foodplanner.Model.Reposatery;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;

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

}
