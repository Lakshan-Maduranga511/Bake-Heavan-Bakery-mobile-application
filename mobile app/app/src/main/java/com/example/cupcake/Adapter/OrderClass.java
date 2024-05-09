package com.example.cupcake.Adapter;

public class OrderClass {
    private String productName;
    private Integer quantity;
    private Integer price;
    private Integer total;
    private String customerName;
    private String mobileNumber;
    public OrderClass(){}
    public OrderClass(String productName, Integer quantity, Integer price, Integer total, String customerName, String mobileNumber) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

