package com.example.cupcake.Adapter;

public class UserClass {
    private String UserName;
    private String Password;


    public UserClass(String userName, String password) {

        UserName=userName;
        Password=password;
    }
    public UserClass(){}
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
