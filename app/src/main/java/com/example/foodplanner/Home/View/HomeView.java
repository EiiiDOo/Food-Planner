package com.example.foodplanner.Home.View;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface HomeView {
        public void showCountry();
        public void showMeal(List<Meal> meals);
        void showrandMeal(Meal meal);
        public void showMealByFirstLetter(List<Meal> meals);
        public void showCategory(List<Categories> categories);
        public void showErrorMsg(String error);
        void saveRandomMeal(Meal meal);
}
