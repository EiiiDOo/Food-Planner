package com.example.foodplanner.Model.RepoRoom.Room;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealsFavLocalDataSource {
    Flowable<List<Meal>> getMealsThatMatchid();

    Completable insertMealFav(Meal meal);

    Completable deleteMealFav(Meal meal);
}
