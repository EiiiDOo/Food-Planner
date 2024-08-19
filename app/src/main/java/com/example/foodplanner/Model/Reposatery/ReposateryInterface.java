package com.example.foodplanner.Model.Reposatery;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;

public interface ReposateryInterface {
    void fetchMealsByFirstLetter(String ch, MealsByFierstLetterCallBack mealsCallBack);
    void fetchRandomMeals(MealsCallBack mealsCallBack);
    void fetchMealsById(String id,MealsCallBack mealsCallBack);
    void fetchMealsByCategory(String category,MealsCallBack mealsCallBack);
    void fetchMealsByCountry(String country,MealsCallBack mealsCallBack);
    void fetchMealsByIngredient(String ingredient, MealsCallBack mealsCallBack);
    public void fetchCategories(CategoryCallback categoryCallback);
    public void fetchIngredients(IngredientCallback ingredientCallback);
    public  void signin(String email,String password, FireBaseCallback fireBaseCallback);
    public  void signUp(String email, String password, FireBaseCallback fireBaseCallback);
    public  void signOut(FireBaseCallback fireBaseCallback);

}
