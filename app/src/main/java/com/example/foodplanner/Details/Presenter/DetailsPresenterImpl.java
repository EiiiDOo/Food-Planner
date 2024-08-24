package com.example.foodplanner.Details.Presenter;

import android.util.Log;

import com.example.foodplanner.Details.View.DetailsView;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsCallBack;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenterImpl implements DetailsPresenterInterface, MealsCallBack, IngredientCallback {
    ReposateryImpl reposatery;
    DetailsView detailsView;
    List<Meal> list = new ArrayList<>();

    public DetailsPresenterImpl(ReposateryImpl reposatery, DetailsView detailsView) {
        this.reposatery = reposatery;
        this.detailsView = detailsView;
        getFavMeals();
    }

    public List<Meal> getList() {
        return list;
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
    public void invokeShowMealWithObj(Meal meal) {

        detailsView.showMealWithObj(meal);
    }

    @Override
    public void addTofav(Meal meal) {
        reposatery.insertFavMeals(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    detailsView.showSuccessMsg("Added Successfully");
                }, e -> {
                    detailsView.showErrorMsg(e.getMessage());
                }).isDisposed();
    }

    @Override
    public void getFavMeals() {
        reposatery.getFavMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> {
                            list = e;
                        }
                        , e -> {
                            detailsView.showErrorMsg(e.getMessage());
                        });

    }


    @Override
    public String getUserId() {
        return reposatery.getFireBaseUser().getUid();
    }


    @Override
    public void onSuccessIngredient(List<Ingredients> ingredients) {

        reposatery.fetchMealsByIngredient(ingredients.get(0).getStrIngredient(), this);
    }

    @Override
    public void onFailureIngredient(String errorMsg) {


        detailsView.showErrorMsg(errorMsg);
    }
    @Override
    public boolean isFav(Meal meal) {
        return list.stream().anyMatch(e -> e.getStrMeal().equals(meal.getStrMeal()));
    }
}
