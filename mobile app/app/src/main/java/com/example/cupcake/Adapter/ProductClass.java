package com.example.cupcake.Adapter;

import java.security.PrivateKey;

public class ProductClass {
   private  String ProductID;

   private String ProductName;

   private  String ProductDescription;

   private String ProductCategory;

    private float Price;
    private Integer Quantity;

    public ProductClass(){}
    public ProductClass(String productID, String productName, String productDescription, String productCategory, float price, int quantity) {
        ProductID = productID;
        ProductName = productName;
        ProductDescription = productDescription;
        ProductCategory = productCategory;
        Price = price;
        Quantity = quantity;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public float getProductPrice() {
        return Price;
    }

    public void setProductPrice(float price) {
        Price = price;
    }

    public Integer getProductQuantity() {
        return Quantity;
    }

    public void setProductQuantity(int quantity) {
        Quantity = quantity;
    }
}
