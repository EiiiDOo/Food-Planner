package com.example.foodplanner.Model.RepoRoom.Room;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealsFavLocalDataSource {
    Flowable<List<Meal>> getMealsThatMatchid(String id);

    Completable insertMealFav(Meal meal);

    Completable deleteMealFav(Meal meal);

    Flowable<List<MealWithDay>> getMealPlan(String userId,String day);

    Flowable<List<MealWithDay>> getMealPlan(String userId);

    Completable deleteMealPlan(MealWithDay mealWithDay);

    Completable insertMealPlan(MealWithDay mealWithDay);
}
