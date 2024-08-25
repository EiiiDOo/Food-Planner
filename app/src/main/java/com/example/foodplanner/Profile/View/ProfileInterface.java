package com.example.foodplanner.Profile.View;

import com.example.foodplanner.Model.MealWithDay;

import java.util.List;

public interface ProfileInterface {
     void setEmail(String email);
     void setDay1(List<MealWithDay> mealWithDays);
     void setDay2(List<MealWithDay> mealWithDays);
     void setDay3(List<MealWithDay> mealWithDays);
     void setDay4(List<MealWithDay> mealWithDays);
     void setDay5(List<MealWithDay> mealWithDays);
     void setDay6(List<MealWithDay> mealWithDays);
     void setDay7(List<MealWithDay> mealWithDays);
     void setDay(List<MealWithDay> mealWithDays,String day);
     void showErrorMsg(String msg);
     void finish();

     void deleteed(MealWithDay mealWithDay);

}
