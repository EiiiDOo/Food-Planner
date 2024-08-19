package com.example.foodplanner.Model;

public class Categories {
    String idCategory,strCategory,strCategoryThumb,strCategoryDescription;

    public Categories(String idCategory, String strCategory, String strCategoryThumb, String strCategoryDescription) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = strCategoryDescription;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }
}
