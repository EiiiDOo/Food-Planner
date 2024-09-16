package com.example.foodplanner.Model.RepoRoom.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsfavLocalDataSourceImpl implements MealsFavLocalDataSource {

    static Context context;
    private MealsDAO dao;
    private static MealsfavLocalDataSourceImpl instance = null;


    public MealsfavLocalDataSourceImpl(Context context) {
        MealsfavLocalDataSourceImpl.context = context;
        AppDataBase db = AppDataBase.getInstance(context);
        dao = db.getMealsDAO();

    }
    public static MealsfavLocalDataSourceImpl getInstance(Context context){
        if(instance == null){
            instance = new MealsfavLocalDataSourceImpl(context);
        }
        MealsfavLocalDataSourceImpl.context = context;
        return instance;
    }

    @Override
    public Flowable<List<Meal>> getMealsThatMatchid(String id) {
        return dao.getMealsThatMatchid(id);

    }

    @Override
    public Completable insertMealFav(Meal meal) {
        return dao.insertMealFav(meal);
    }

    @Override
    public Completable deleteMealFav(Meal meal) {
        return dao.deleteMealFav(meal);
    }

    @Override
    public Flowable<List<MealWithDay>> getMealPlan(String userId, String day) {
        return dao.getMealPlan(userId , day);

    }@Override
    public Flowable<List<MealWithDay>> getMealPlan(String userId) {
        return dao.getMealPlan(userId );
    }

    @Override
    public Completable deleteMealPlan(MealWithDay mealWithDay) {
        return dao.deleteMealPlan(mealWithDay);
    }

    @Override
    public Completable insertMealPlan(MealWithDay mealWithDay) {
        return dao.insertMealPlan(mealWithDay);
    }

    @Override
    public Completable insertMealPlan(List<MealWithDay> mealWithDay) {
        return dao.insertMealPlan(mealWithDay);
    }

    @Override
    public Completable insertMealFav(List<Meal> mealList) {
        return dao.insertMealFav(mealList);
    }
}
