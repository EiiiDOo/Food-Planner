package com.example.foodplanner.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Meal {
    String idMeal, strMeal, strCategory, strArea, strInstructions, strMealThumb, strYoutube, strIngredient1,strIngredient2,strIngredient3,
            strIngredient4,strIngredient5,strIngredient6,strIngredient7,strIngredient8,strIngredient9,strIngredient10,strIngredient11,
            strIngredient12,strIngredient13,strIngredient14,strIngredient15,strIngredient16,strIngredient17,strIngredient18,strIngredient19,
            strIngredient20,strMeasure,strMeasure2,strMeasure3,strMeasure4,strMeasure5,strMeasure6,strMeasure7,strMeasure8,strMeasure9,
            strMeasure10,strMeasure11, strMeasure12,strMeasure13,strMeasure14,strMeasure15,strMeasure16,strMeasure17,strMeasure18,strMeasure19,
            strMeasure20,strSource;
    public List<Ingredients> getIngredients() {
        List<Ingredients> ingredients = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().startsWith("strIngredient")) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null && !value.isEmpty()) {
                        ingredients.add(new Ingredients(value));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return ingredients;
    }

    public List<String> getMeasures() {
        List<String> ingredients = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().startsWith("measure")) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null) {
                        ingredients.add(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return ingredients;}

    public String getIdMeal() {
        return idMeal;
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

    public String getStrMeasure() {
        return strMeasure;
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
}
