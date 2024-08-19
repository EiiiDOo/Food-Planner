package com.example.foodplanner.Details.View;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface DetailsView {
    void showMeal(List<Meal> meal);
    void showErrorMsg(String error);
    int getResourceId(String name);
}
