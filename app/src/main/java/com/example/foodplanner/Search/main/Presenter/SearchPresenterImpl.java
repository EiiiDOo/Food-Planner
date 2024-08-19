package com.example.foodplanner.Search.main.Presenter;

import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Search.main.View.SearchInterfaceView;

import java.util.List;

public class SearchPresenterImpl implements CategoryCallback, IngredientCallback {
    ReposateryImpl reposatery;
    SearchInterfaceView searchView;

    public SearchPresenterImpl(ReposateryImpl reposatery, SearchInterfaceView searchView) {
        this.searchView = searchView;
        this.reposatery = reposatery;
        reposatery.fetchCategories(this);
        reposatery.fetchIngredients(this);
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
}
