package com.example.foodplanner.Profile.Presenter;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;

import java.util.ArrayList;
import java.util.List;

public interface ProfilePresenter {
    String getEmail();

    void clear();
    void logout();

    void delete(MealWithDay mealWithDay);

    void setMealWithDay(MealWithDay mealWithDays);

    void backupAllMeals(FireBaseCallback callback);

    void downloadAll(FireBaseCallback callback);

    void restoreAllFavouriteMeals(List<Meal> meals);

    void restoreAllPlanMeals(List<MealWithDay> meals);

}
