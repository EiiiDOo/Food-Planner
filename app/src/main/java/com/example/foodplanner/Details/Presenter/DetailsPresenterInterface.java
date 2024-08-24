package com.example.foodplanner.Details.Presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface DetailsPresenterInterface {
    void fetchMealsById(String id);

    void invokeShowMealWithObj(Meal meal);

    void addTofav(Meal meal);

    void getFavMeals();

    String getUserId();
    boolean isFav(Meal meal);

}
