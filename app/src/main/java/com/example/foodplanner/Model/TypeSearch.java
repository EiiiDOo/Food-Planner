package com.example.foodplanner.Model;

import java.io.Serializable;

public class TypeSearch implements Serializable {
    public enum Type{
        CATEGORIES,
        INGREDIENTS,
        COUNTRIES,
        MealsByNmae
    }
    String param;
    Type type;
    public TypeSearch(String param, Type type) {
        this.param = param;
        this.type = type;
    }

    public TypeSearch(Type type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public Type getType() {
        return type;
    }
}
