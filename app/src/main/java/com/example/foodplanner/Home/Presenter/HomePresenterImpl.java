package com.example.foodplanner.Home.Presenter;

import android.util.Log;

import com.example.foodplanner.Home.View.HomeView;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;

import java.util.List;
import java.util.Random;

public class HomePresenterImpl implements MealsCallBack, CategoryCallback, HomePresenter {
    ReposateryImpl repo;
    HomeView homeView;

    public HomePresenterImpl(ReposateryImpl repo, HomeView homeView) {
        this.homeView = homeView;
        this.repo = repo;
        repo.fetchRandomMeals(this);
        repo.fetchCategories(this);
        repo.fetchMealsByName(getRandomLowercaseLetter(), this);

    }

    @Override
    public void onSuccessMeals(List<Meal> Meals) {
        if(Meals == null)
            homeView.showErrorMsg("No Meal Found");
        else
            homeView.showMeal(Meals);
    }

    @Override
    public void onFailureMeals(String errorMsg) {
        homeView.showErrorMsg(errorMsg);
    }

    @Override
    public void onSuccessCategory(List<Categories> categories) {
        homeView.showCategory(categories);
        homeView.showCountry();
    }

    @Override
    public void onFailureCategory(String errorMsg) {
        homeView.showErrorMsg(errorMsg);
    }



    public String getRandomLowercaseLetter() {
        Random random = new Random();
        return String.valueOf((char) (random.nextInt(26) + 'a'));
    }
}
