package com.example.foodplanner.Model.RepoRoom.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsDAO {
    @Query("SELECT * FROM meal_fav_Table")
    Flowable<List<Meal>> getAllMeals();

    @Query("SELECT * FROM meal_fav_table WHERE userId = :userId ")
    Flowable<List<Meal>> getMealsThatMatchid(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealFav(Meal meal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealFav(List<Meal> meal);

    @Delete
    Completable deleteMealFav(Meal meal);


    @Query(("SELECT * FROM meal_plan_table WHERE userId = :userId AND day = :day"))
    Flowable<List<MealWithDay>> getMealPlan(String userId, String day);

    @Query(("SELECT * FROM meal_plan_table WHERE userId = :userId"))
    Flowable<List<MealWithDay>> getMealPlan(String userId);

    @Delete
    Completable deleteMealPlan(MealWithDay mealWithDay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealPlan(MealWithDay mealWithDay);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealPlan(List<MealWithDay> mealWithDay);
}
