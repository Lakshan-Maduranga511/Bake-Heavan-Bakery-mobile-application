package com.example.cupcake.DBClass;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnecter extends SQLiteOpenHelper {

    public  DBConnecter(Context context) {
        super(context, "Bakery", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table UserInfo (UserName VARCHAR PRIMARY KEY  NOT NULL,Password VARCHAR)");
        db.execSQL("create table CategoryTable (CategoryID VARCHAR PRIMARY KEY  NOT NULL,CategoryName VARCHAR)");
        db.execSQL("create table ProductTable (ProductID VARCHAR PRIMARY KEY  NOT NULL,ProductName VARCHAR, Price FLOAT,Quantity INTEGER,Description VARCHAR,ProductCategory VARCHAR )");
        db.execSQL("CREATE TABLE BuyProductTable (OrderID INTEGER PRIMARY KEY AUTOINCREMENT, ProductName VARCHAR, Quantity INTEGER,Price INTEGER,Total INTEGER, CustomerName VARCHAR, CustomerMobileNumber VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
