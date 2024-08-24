package com.example.foodplanner.Details.Presenter;

import com.example.foodplanner.Details.View.DetailsView;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsCallBack;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenterImpl implements DetailsPresenterInterface, MealsCallBack, IngredientCallback {
    ReposateryImpl reposatery;
    DetailsView detailsView;
    List<Meal> listMeal = new ArrayList<>();
    List<MealWithDay> listMealWithDay = new ArrayList<>();

    public List<MealWithDay> getListMealWithDay() {
        return listMealWithDay;
    }

    public DetailsPresenterImpl(ReposateryImpl reposatery, DetailsView detailsView) {
        this.reposatery = reposatery;
        this.detailsView = detailsView;
        getPlanMeals();
    }

    public List<Meal> getList() {
        return listMeal;
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
        reposatery.getFavMeals(reposatery.getFireBaseUser().getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> {
                            listMeal = e;
                        }
                        , e -> {
                            detailsView.showErrorMsg(e.getMessage());
                        });

    }

    public void getPlanMeals() {
        reposatery.getMealPlan(reposatery.getFireBaseUser().getUid())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> {
                    listMealWithDay = e;
                }, e -> {
                    detailsView.showErrorMsg(e.getMessage());
                });

    }

    public void addPlan(MealWithDay mealWithDay) {
        reposatery.insertMealPlan(mealWithDay).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    listMealWithDay.add(mealWithDay);
                    detailsView.showSuccessMsg("Added Successfully");
                }, e -> {
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
        return listMeal.stream().anyMatch(e -> e.getStrMeal().equals(meal.getStrMeal()));
    }

    @Override
    public boolean isPlan(MealWithDay meal) {
        return listMealWithDay.stream().anyMatch(e -> e.getStrMeal().equals(meal.getStrMeal()));
    }

}
