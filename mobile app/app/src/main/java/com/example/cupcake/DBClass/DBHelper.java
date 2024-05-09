package com.example.cupcake.DBClass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.cupcake.Adapter.CategoryClass;
import com.example.cupcake.Adapter.OrderClass;
import com.example.cupcake.Adapter.ProductClass;
import com.example.cupcake.Adapter.UserClass;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private Context context;
    private SQLiteDatabase dbDatabase;

    public DBHelper(Context context) {
        this.context = context;
    }

    public DBHelper OpenDB() {
        DBConnecter dbCon = new DBConnecter(context);
        dbDatabase = dbCon.getWritableDatabase();
        return this;
    }

    public boolean CreateNewUser(UserClass userClass) {
        try {
            dbDatabase.execSQL("insert into UserInfo values('" + userClass.getUserName() + "','" + userClass.getPassword() + "')");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<UserClass> ValidLogin(String UserName, String Password) {
        ArrayList<UserClass> userList = new ArrayList<UserClass>();
        try {

            Cursor cursor = dbDatabase.rawQuery("SELECT * FROM UserInfo WHERE UserName=? AND Password=?", new String[]{UserName, Password});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    UserClass user = new UserClass();
                    int userNameIndex = cursor.getColumnIndex("UserName");
                    int passwordIndex = cursor.getColumnIndex("Password");
                    if (userNameIndex != -1 && passwordIndex != -1) {
                        user.setUserName(cursor.getString(userNameIndex));
                        user.setPassword(cursor.getString(passwordIndex));
                        userList.add(user);
                    }
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }


    public boolean CreateNewCategory(CategoryClass categoryClass) {
        try {
            dbDatabase.execSQL("insert into CategoryTable values('" + categoryClass.getCategoryID() + "','" + categoryClass.getCategoryName() + "')");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String> getcategorynames() {
        List<String> categoryNames = new ArrayList<>();
        try {
            Cursor cursor = dbDatabase.rawQuery("SELECT CategoryName FROM CategoryTable", null);
            if (cursor != null) {
                int columnNameIndex = cursor.getColumnIndex("CategoryName");
                if (columnNameIndex != -1) {
                    while (cursor.moveToNext()) {
                        String categoryName = cursor.getString(columnNameIndex);
                        categoryNames.add(categoryName);
                    }
                } else {

                    Log.e("DBHelper", "Column 'CategoryName' not found in the result set.");
                }
                cursor.close();
            } else {

                Log.e("DBHelper", "Cursor is null.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categoryNames;
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        try {
            Cursor cursor = dbDatabase.rawQuery("SELECT ProductName FROM ProductTable", null);
            if (cursor != null) {
                int columnNameIndex = cursor.getColumnIndex("ProductName");
                if (columnNameIndex != -1) {
                    while (cursor.moveToNext()) {
                        String productName = cursor.getString(columnNameIndex);
                        productNames.add(productName);
                    }
                } else {

                    Log.e("DBHelper", "Column 'ProductName' not found in the result set.");
                }
                cursor.close();
            } else {

                Log.e("DBHelper", "Cursor is null.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productNames;
    }

    public boolean AddNewProduct(ProductClass productClass) {
        try {
            dbDatabase.execSQL("INSERT INTO ProductTable (ProductID, ProductName, Price, ProductCategory, Description, Quantity) VALUES ('" +
                    productClass.getProductID() + "', '" +
                    productClass.getProductName() + "', " +
                    productClass.getProductPrice() + ", '" +
                    productClass.getProductCategory() + "', '" +
                    productClass.getProductDescription() + "', " +
                    productClass.getProductQuantity() + ")");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean DeleteCategory(String categoryName) {
        try {
            // Delete from ProductTable
            dbDatabase.execSQL("DELETE FROM ProductTable WHERE ProductCategory = '" + categoryName + "'");

            // Delete from CategoryTable
            dbDatabase.execSQL("DELETE FROM CategoryTable WHERE CategoryName = '" + categoryName + "'");

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean editCategory(String categoryType, String newCategory) {
        try {
            //update  category table
            dbDatabase.execSQL("UPDATE CategoryTable SET CategoryName = '" + newCategory + "' WHERE CategoryName = '" + categoryType + "'");

            // update product table
            dbDatabase.execSQL("UPDATE ProductTable SET ProductCategory = '" + newCategory + "' WHERE ProductCategory = '" + categoryType + "'");

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean deleteProduct(String productID, String productName) {
        try {

            dbDatabase.execSQL("DELETE FROM ProductTable WHERE ProductID = '" + productID + "' AND ProductName = '" + productName + "'");


            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean editProduct(String productID, String productName, String productDescription,
                               String productPrice, String productQuantity, String productCategory) {
        try {

            dbDatabase.execSQL("UPDATE ProductTable SET ProductName = '" + productName + "', "
                    + "Description = '" + productDescription + "', "
                    + "Price = '" + productPrice + "', "
                    + "Quantity = '" + productQuantity + "', "
                    + "ProductCategory = '" + productCategory + "' "
                    + "WHERE ProductID = '" + productID + "'");


            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<ProductClass> SearchProduct(String SelectedCategory) {
        ArrayList<ProductClass> productList = new ArrayList<>();

        try {


            Cursor cursor = dbDatabase.rawQuery("Select * from ProductTable where ProductCategory='" +
                    SelectedCategory + "' ", null);
            if (cursor.moveToFirst()) {
                do {
                    ProductClass product = new ProductClass();
                    product.setProductID(  cursor.getString(0));
                    product.setProductName(  cursor.getString(1));
                    product.setProductPrice(Float.parseFloat(cursor.getString(2)));
                    product.setProductQuantity(Integer.parseInt( cursor.getString(3)));
                    product.setProductDescription( cursor.getString(4));
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    public long AddOrder(OrderClass order) {
        long orderId = -1; // Default value indicating insertion failure
        try {
            dbDatabase.execSQL("INSERT INTO BuyProductTable (ProductName, Quantity, Price, Total, CustomerName, CustomerMobileNumber) VALUES ('" +
                    order.getProductName() + "', " +
                    order.getQuantity() + ", " +
                    order.getPrice() + ", " +
                    order.getTotal() + ", '" +
                    order.getCustomerName() + "', '" +
                    order.getMobileNumber() + "')");

            // Retrieve the last inserted row ID (order ID)
            Cursor cursor = dbDatabase.rawQuery("SELECT last_insert_rowid()", null);
            if (cursor != null && cursor.moveToFirst()) {
                orderId = cursor.getLong(0);
                cursor.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderId;
    }

    public ArrayList<OrderClass> SearchOrder(String orderId) {
        ArrayList<OrderClass> OrderList = new ArrayList<OrderClass>();
        try {
            Cursor cursor = dbDatabase.rawQuery("SELECT ProductName, Quantity, Price, Total, CustomerName, CustomerMobileNumber FROM BuyProductTable WHERE OrderID='" + orderId + "'", null);


            if (cursor.moveToFirst()) {
                do {
                    OrderClass order = new OrderClass();
                    order.setProductName(cursor.getString(0));
                    order.setQuantity(cursor.getInt(1));
                    order.setPrice(cursor.getInt(2));
                    order.setTotal(cursor.getInt(3));
                    order.setCustomerName(cursor.getString(4));
                    order.setMobileNumber(cursor.getString(5));
                    OrderList.add(order);
                } while (cursor.moveToNext());
            }
            cursor.close(); // Close the cursor to avoid memory leaks
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return OrderList;
    }
}