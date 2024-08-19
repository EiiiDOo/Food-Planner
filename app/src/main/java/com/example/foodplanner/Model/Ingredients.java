package com.example.foodplanner.Model;

public class Ingredients {
    String idIngredient,strIngredient;

    public Ingredients(String idIngredient, String strIngredient) {
        this.idIngredient = idIngredient;
        this.strIngredient = strIngredient;
    }

    public Ingredients(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }
}
