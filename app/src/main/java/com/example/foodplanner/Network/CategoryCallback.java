package com.example.foodplanner.Network;

import com.example.foodplanner.Model.Categories;

import java.util.List;

public interface CategoryCallback {
    public void onSuccessCategory(List<Categories> categories);
    public void onFailureCategory(String errorMsg);
}
