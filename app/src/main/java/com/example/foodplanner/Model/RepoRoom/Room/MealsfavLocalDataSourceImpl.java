package com.example.foodplanner.Model.RepoRoom.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.foodplanner.Model.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsfavLocalDataSourceImpl implements MealsFavLocalDataSource {

    static Context context;
    private Flowable<List<Meal>> favMeal;
    private MealsDAO dao;
    private static MealsfavLocalDataSourceImpl instance = null;
    static SharedPreferences sp;
    static String id ;




    public MealsfavLocalDataSourceImpl(Context context) {
        MealsfavLocalDataSourceImpl.context = context;
        AppDataBase db = AppDataBase.getInstance(context);
        dao = db.getMealsDAO();
        sp = context.getSharedPreferences("userdetails", 0x0000);
        id=sp.getString("user", null);
        favMeal = dao.getMealsThatMatchid(id);
        Log.d("FavouritePresenterImpl", "MealsfavLocalDataSourceImpl: "+id);

    }
    public static MealsfavLocalDataSourceImpl getInstance(Context context){
        if(instance == null){
            instance = new MealsfavLocalDataSourceImpl(context);
        }
        MealsfavLocalDataSourceImpl.context = context;
        return instance;
    }

    @Override
    public Flowable<List<Meal>> getMealsThatMatchid() {
        return favMeal;

    }

    @Override
    public Completable insertMealFav(Meal meal) {
        return dao.insertMealFav(meal);
    }

    @Override
    public Completable deleteMealFav(Meal meal) {
        return dao.deleteMealFav(meal);
    }
}
