package com.example.foodplanner.Network;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface MealsCallBack {
    public void onSuccessMeals(List<Meal> Meals);
    public void onFailureMeals(String errorMsg);
}
