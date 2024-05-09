package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cupcake.R;

public class AdminMenuActivity extends AppCompatActivity {
    ImageView addCategory, addProduct, manageCategory, manageProduct, viewOrders, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);


        addCategory = findViewById(R.id.addcategory);
        addProduct = findViewById(R.id.addprodct);
        manageCategory = findViewById(R.id.managecategory);
        manageProduct = findViewById(R.id.manageproduct);
        viewOrders = findViewById(R.id.vieworder);
        logout = findViewById(R.id.logout);


        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddCategoryActivity
                startActivity(new Intent(AdminMenuActivity.this, AddCategoryActivity.class));
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddProductActivity
                startActivity(new Intent(AdminMenuActivity.this, AddProductActivity.class));
            }
        });

        manageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ManageCategoryActivity
                startActivity(new Intent(AdminMenuActivity.this, ManageCatageryActivity.class));
            }
        });

        manageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminMenuActivity.this, ManageProductActivity.class));
            }
        });

        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminMenuActivity.this, ViewOrderActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminMenuActivity.this, LoginActivity.class));
            }
        });
    }
}
