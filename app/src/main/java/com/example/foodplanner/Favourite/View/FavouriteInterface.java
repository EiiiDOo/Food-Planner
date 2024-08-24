package com.example.foodplanner.Favourite.View;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface FavouriteInterface {
    void showFavMeals(List<Meal> list);
    void showErrorMsg(String msg);
    void showSuccessMsg(String msg);
    void ifIsEmpty(boolean b);
    void deletedSuccessfully(Meal meal);
}
