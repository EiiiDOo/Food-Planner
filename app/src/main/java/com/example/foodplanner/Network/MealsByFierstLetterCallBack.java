package com.example.foodplanner.Network;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface MealsByFierstLetterCallBack {
    public void onSuccessMealsByFirstLetter(List<Meal> Meals);
    public void onFailureMealsByFirstLetter(String errorMsg);
}
