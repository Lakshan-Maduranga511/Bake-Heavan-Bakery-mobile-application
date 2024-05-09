package com.example.cupcake.Adapter;

public class CategoryClass {

    private String CategoryID;

    private  String categoryName;

    public CategoryClass(String categoryID, String categoryName) {
        CategoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
