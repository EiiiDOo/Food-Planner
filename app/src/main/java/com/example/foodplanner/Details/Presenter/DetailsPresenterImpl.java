package com.example.foodplanner.Details.Presenter;

import com.example.foodplanner.Details.View.DetailsView;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsCallBack;

import java.util.List;

public class DetailsPresenterImpl implements DetailsPresenterInterface, MealsCallBack, IngredientCallback {
    ReposateryImpl reposatery;
    DetailsView detailsView;

    public DetailsPresenterImpl(ReposateryImpl reposatery, DetailsView detailsView) {
        this.reposatery = reposatery;
        this.detailsView = detailsView;
    }

    @Override
    public void onSuccessMeals(List<Meal> Meals) {

        detailsView.showMeal(Meals);
    }

    @Override
    public void onFailureMeals(String errorMsg) {

        detailsView.showErrorMsg(errorMsg);
    }

    @Override
    public void fetchMealsById(String id) {

        reposatery.fetchMealsById(id, this);
    }

    @Override
    public void fetchIngredientById(String id) {

    }

    @Override
    public void onSuccessIngredient(List<Ingredients> ingredients) {

        reposatery.fetchMealsByIngredient(ingredients.get(0).getStrIngredient(), this);
    }

    @Override
    public void onFailureIngredient(String errorMsg) {


        detailsView.showErrorMsg(errorMsg);
    }
}
