package com.example.foodplanner.Favourite.Presnter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface favouritePresemterInterface {
    void getListOfFavMeal();
    String getUserId();
    void deleteFromFav(Meal meal);
}
