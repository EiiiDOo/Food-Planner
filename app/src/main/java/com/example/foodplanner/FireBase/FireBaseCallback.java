package com.example.foodplanner.FireBase;

import com.example.foodplanner.Model.Categories;

import java.util.List;

public interface FireBaseCallback {
    public void onSuccess();
    public void onFailure(String errorMsg);
}
