package com.example.foodplanner.Search.Searchby.Presenter;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.Search.Searchby.View.SearchByView;

import java.util.List;

public class SearchByPresenterImpl implements SearchByPresenter , MealsCallBack {
    ReposateryImpl reposatery;
    SearchByView searchByView;

    public SearchByPresenterImpl(ReposateryImpl reposatery, SearchByView searchByView) {
        this.reposatery = reposatery;
        this.searchByView = searchByView;
    }

    @Override
    public void onSuccessMeals(List<Meal> Meals) {
        searchByView.showMeal(Meals);
    }

    @Override
    public void onFailureMeals(String errorMsg) {

        searchByView.showErrorMsg(errorMsg);
    }

    @Override
    public void fetchMealsByCategory(String category) {

        reposatery.fetchMealsByCategory(category, this);
    }

    @Override
    public void fetchMealsByIngredient(String ingredient) {

        reposatery.fetchMealsByIngredient(ingredient, this);
    }

    @Override
    public void fetchMealsByCountry(String category) {

        reposatery.fetchMealsByCountry(category, this);
    }
}
