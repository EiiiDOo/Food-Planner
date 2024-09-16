package com.example.foodplanner.Details.Presenter;

import android.util.Log;

import com.example.foodplanner.Details.View.DetailsView;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.DialogFragment;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsPresenterImpl implements DetailsPresenterInterface, MealsCallBack, IngredientCallback {
    private static DetailsPresenterImpl instance = null;
    ReposateryImpl reposatery;
    DetailsView detailsView;
    volatile List<Meal> listMeal = MainActivity.list;
    List<MealWithDay> listMealWithDay = new ArrayList<>();
    CompositeDisposable compositeDisposable;


     public DetailsPresenterImpl(ReposateryImpl reposatery, DetailsView detailsView) {
        this.reposatery = reposatery;
        this.detailsView = detailsView;
        compositeDisposable = new CompositeDisposable();


    }
    public static DetailsPresenterImpl getInstance(ReposateryImpl reposatery, DetailsView detailsView) {
        if(instance == null) {
            instance = new DetailsPresenterImpl(reposatery, detailsView);
        }
        return instance;
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
        compositeDisposable.add(reposatery.fetchMealsById(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e-> detailsView.showMeal(e.getMeals()), e -> detailsView.showErrorMsg(e.getMessage())));
    }

    @Override
    public void invokeShowMealWithObj(Meal meal) {
        detailsView.showMealWithObj(meal);
    }

    @Override
    public void addTofav(Meal meal) {
        compositeDisposable.add(reposatery.insertFavMeals(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->  detailsView.showSuccessMsg("Added Successfully")
                , e -> detailsView.showErrorMsg(e.getMessage())));
    }

    @Override
    public void getFavMeals() {
        compositeDisposable.add(reposatery.getFavMeals(MainActivity.statUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e ->  listMeal = e , e -> detailsView.showErrorMsg(e.getMessage())));

    }


    public void addPlan(MealWithDay mealWithDay) {
        compositeDisposable.add(reposatery.insertMealPlan(mealWithDay).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    listMealWithDay.add(mealWithDay);
                    detailsView.showSuccessMsg("Added Successfully");
                }, e -> detailsView.showErrorMsg(e.getMessage())));
    }


    @Override
    public String getUserId() {
        return reposatery.getFireBaseUser().getUid();
    }


    @Override
    public void onSuccessIngredient(List<Ingredients> ingredients) {

        compositeDisposable.add(reposatery.fetchMealsByIngredient(ingredients.get(0).getStrIngredient()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> detailsView.showMeal(e.getMeals()), e -> detailsView.showErrorMsg(e.getMessage())));
    }

    @Override
    public void onFailureIngredient(String errorMsg) {
        detailsView.showErrorMsg(errorMsg);
    }

    @Override
    public boolean isFav(Meal meal) {
        Log.d("TAG", "isFav: "+listMeal);
        return listMeal.stream().anyMatch(e -> e.getStrMeal().equals(meal.getStrMeal()));
    }

    @Override
    public boolean isPlan(MealWithDay meal) {
        return listMealWithDay.stream().anyMatch(e -> e.getStrMeal().equals(meal.getStrMeal()));
    }

}
