package com.example.foodplanner.Model.RepoRoom.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;

@Database(entities = {Meal.class, MealWithDay.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null ;
    public abstract MealsDAO getMealsDAO();
    public static synchronized AppDataBase getInstance(Context context) {
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "database").build();
        return instance;
    }
}
