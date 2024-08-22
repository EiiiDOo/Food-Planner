package com.example.foodplanner.Search.main.Presenter;

import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.Search.main.View.SearchInterfaceView;

import java.util.List;
import java.util.Random;

public class SearchPresenterImpl implements CategoryCallback, IngredientCallback, MealsCallBack {
    ReposateryImpl reposatery;
    SearchInterfaceView searchView;

    public SearchPresenterImpl(ReposateryImpl reposatery, SearchInterfaceView searchView) {
        this.searchView = searchView;
        this.reposatery = reposatery;
        reposatery.fetchCategories(this);
        reposatery.fetchIngredients(this);
        reposatery.fetchMealsByName(getRandomLowercaseLetter(), this);
    }

    @Override
    public void onSuccessCategory(List<Categories> categories) {
        searchView.showCategory(categories);
        searchView.showCountry(AllCountries.getInstance().getAllCountries());
    }

    @Override
    public void onFailureCategory(String errorMsg) {
        searchView.showErrorMsg(errorMsg);
    }

    @Override
    public void onSuccessIngredient(List<Ingredients> ingredients) {
        searchView.showIngredient(ingredients);
    }

    @Override
    public void onFailureIngredient(String errorMsg) {
        searchView.showErrorMsg(errorMsg);
    }

    public String getRandomLowercaseLetter() {
        Random random = new Random();
        return String.valueOf((char) (random.nextInt(26) + 'a'));
    }

    @Override
    public void onSuccessMeals(List<Meal> Meals) {
        searchView.showMealsByName(Meals);
    }

    @Override
    public void onFailureMeals(String errorMsg) {
        searchView.showErrorMsg(errorMsg);

    }
}
