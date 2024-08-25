package com.example.foodplanner.Profile.Presenter;

import com.example.foodplanner.Model.MealWithDay;

public interface ProfilePresenter {
    String getEmail();

    void clear();
    void logout();

    void delete(MealWithDay mealWithDay);

    void setMealWithDay(MealWithDay mealWithDays);

    void getMealPlanWithIdday();
}
