package com.example.foodplanner.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.Gson;

@Entity(tableName = "meal_plan_table", primaryKeys = {"userId", "idMeal", "day"})
public class MealWithDay {
    @NonNull
    String userId,idMeal, day;
    String strMeal, strCategory, strArea, strInstructions, strMealThumb, strYoutube, strIngredient1, strIngredient2, strIngredient3,
            strIngredient4, strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11,
            strIngredient12, strIngredient13, strIngredient14, strIngredient15, strIngredient16, strIngredient17, strIngredient18, strIngredient19,
            strIngredient20, strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
            strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15, strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20, strSource;

    public MealWithDay() {
    }

    @Ignore
    public MealWithDay(MealWithDay mealWithDay) {
        this.userId = mealWithDay.userId;
        this.idMeal = mealWithDay.idMeal;
        this.day = mealWithDay.day;
        this.strMeal = mealWithDay.strMeal;
        this.strCategory = mealWithDay.strCategory;
        this.strArea = mealWithDay.strArea;
        this.strInstructions = mealWithDay.strInstructions;
        this.strMealThumb = mealWithDay.strMealThumb;
        this.strYoutube = mealWithDay.strYoutube;
        this.strIngredient1 = mealWithDay.strIngredient1;
        this.strIngredient2 = mealWithDay.strIngredient2;
        this.strIngredient3 = mealWithDay.strIngredient3;
        this.strIngredient4 = mealWithDay.strIngredient4;
        this.strIngredient5 = mealWithDay.strIngredient5;
        this.strIngredient6 = mealWithDay.strIngredient6;
        this.strIngredient7 = mealWithDay.strIngredient7;
        this.strIngredient8 = mealWithDay.strIngredient8;
        this.strIngredient9 = mealWithDay.strIngredient9;
        this.strIngredient10 = mealWithDay.strIngredient10;
        this.strIngredient11 = mealWithDay.strIngredient11;
        this.strIngredient12 = mealWithDay.strIngredient12;
        this.strIngredient13 = mealWithDay.strIngredient13;
        this.strIngredient14 = mealWithDay.strIngredient14;
        this.strIngredient15 = mealWithDay.strIngredient15;
        this.strIngredient16 = mealWithDay.strIngredient16;
        this.strIngredient17 = mealWithDay.strIngredient17;
        this.strIngredient18 = mealWithDay.strIngredient18;
        this.strIngredient19 = mealWithDay.strIngredient19;
        this.strIngredient20 = mealWithDay.strIngredient20;
        this.strMeasure1 = mealWithDay.strMeasure1;
        this.strMeasure2 = mealWithDay.strMeasure2;
        this.strMeasure3 = mealWithDay.strMeasure3;
        this.strMeasure4 = mealWithDay.strMeasure4;
        this.strMeasure5 = mealWithDay.strMeasure5;
        this.strMeasure6 = mealWithDay.strMeasure6;
        this.strMeasure7 = mealWithDay.strMeasure7;
        this.strMeasure8 = mealWithDay.strMeasure8;
        this.strMeasure9 = mealWithDay.strMeasure9;
        this.strMeasure10 = mealWithDay.strMeasure10;
        this.strMeasure11 = mealWithDay.strMeasure11;
        this.strMeasure12 = mealWithDay.strMeasure12;
        this.strMeasure13 = mealWithDay.strMeasure13;
        this.strMeasure14 = mealWithDay.strMeasure14;
        this.strMeasure15 = mealWithDay.strMeasure15;
        this.strMeasure16 = mealWithDay.strMeasure16;
        this.strMeasure17 = mealWithDay.strMeasure17;
        this.strMeasure18 = mealWithDay.strMeasure18;
        this.strMeasure19 = mealWithDay.strMeasure19;
        this.strMeasure20 = mealWithDay.strMeasure20;
        this.strSource = mealWithDay.strSource;
    }

    public Meal transferToMeal(MealWithDay meal) {
        Meal res = new Meal();
        res.setIdMeal(meal.getIdMeal());
        res.setStrArea(meal.getStrArea());
        res.setStrCategory(meal.getStrCategory());
        res.setStrInstructions(meal.getStrInstructions());
        res.setStrMeal(meal.getStrMeal());
        res.setStrMealThumb(meal.getStrMealThumb());
        res.setStrYoutube(meal.getStrYoutube());
        res.setStrSource(meal.getStrSource());
        res.setStrIngredient1(meal.getStrIngredient1());
        res.setStrIngredient2(meal.getStrIngredient2());
        res.setStrIngredient3(meal.getStrIngredient3());
        res.setStrIngredient4(meal.getStrIngredient4());
        res.setStrIngredient5(meal.getStrIngredient5());
        res.setStrIngredient6(meal.getStrIngredient6());
        res.setStrIngredient7(meal.getStrIngredient7());
        res.setStrIngredient8(meal.getStrIngredient8());
        res.setStrIngredient9(meal.getStrIngredient9());
        res.setStrIngredient10(meal.getStrIngredient10());
        res.setStrIngredient11(meal.getStrIngredient11());
        res.setStrIngredient12(meal.getStrIngredient12());
        res.setStrIngredient13(meal.getStrIngredient13());
        res.setStrIngredient14(meal.getStrIngredient14());
        res.setStrIngredient15(meal.getStrIngredient15());
        res.setStrIngredient16(meal.getStrIngredient16());
        res.setStrIngredient17(meal.getStrIngredient17());
        res.setStrIngredient18(meal.getStrIngredient18());
        res.setStrIngredient19(meal.getStrIngredient19());
        res.setStrIngredient20(meal.getStrIngredient20());
        res.setStrMeasure1(meal.getStrMeasure1());
        res.setStrMeasure2(meal.getStrMeasure2());
        res.setStrMeasure3(meal.getStrMeasure3());
        res.setStrMeasure4(meal.getStrMeasure4());
        res.setStrMeasure5(meal.getStrMeasure5());
        res.setStrMeasure6(meal.getStrMeasure6());
        res.setStrMeasure7(meal.getStrMeasure7());
        res.setStrMeasure8(meal.getStrMeasure8());
        res.setStrMeasure9(meal.getStrMeasure9());
        res.setStrMeasure10(meal.getStrMeasure10());
        res.setStrMeasure11(meal.getStrMeasure11());
        res.setStrMeasure12(meal.getStrMeasure12());
        res.setStrMeasure13(meal.getStrMeasure13());
        res.setStrMeasure14(meal.getStrMeasure14());
        res.setStrMeasure15(meal.getStrMeasure15());
        res.setStrMeasure16(meal.getStrMeasure16());
        res.setStrMeasure17(meal.getStrMeasure17());
        res.setStrMeasure18(meal.getStrMeasure18());
        res.setStrMeasure19(meal.getStrMeasure19());
        res.setStrMeasure20(meal.getStrMeasure20());
        res.setDay(meal.getDay());
        return res;
    }


    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    @NonNull
    public String getDay() {
        return day;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    public void setStrMeasure16(String strMeasure16) {
        this.strMeasure16 = strMeasure16;
    }

    public void setStrMeasure17(String strMeasure17) {
        this.strMeasure17 = strMeasure17;
    }

    public void setStrMeasure18(String strMeasure18) {
        this.strMeasure18 = strMeasure18;
    }

    public void setStrMeasure19(String strMeasure19) {
        this.strMeasure19 = strMeasure19;
    }

    public void setStrMeasure20(String strMeasure20) {
        this.strMeasure20 = strMeasure20;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
