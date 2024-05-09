package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cupcake.R;

public class UserMenuActivity extends AppCompatActivity {
    TextView greetingTextView;


    ImageView ViewCategory, PlaceOrder, vieworder, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        greetingTextView = findViewById(R.id.greetingTextView);

        String customerName = getIntent().getStringExtra("customerName");
        String greetingMessage = "Hi "+ " " + customerName;
        greetingTextView.setText(greetingMessage);


        ViewCategory = findViewById(R.id.ViewCategory);
        PlaceOrder = findViewById(R.id.PlaceOrder);
        vieworder = findViewById(R.id.vieworder);
        logout = findViewById(R.id.logout);


        ViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle onClick for View Category
                Intent intent = new Intent(UserMenuActivity.this, ViewCategoriesActivity.class);
                startActivity(intent);
            }
        });

        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle onClick for Place Order
                Intent intent = new Intent(UserMenuActivity.this, CupcakeOrderActivity.class);
                startActivity(intent);
            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle onClick for View Orders
                Intent intent = new Intent(UserMenuActivity.this, ViewOrderActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle onClick for Logout
                // You can implement your logout logic here
                // For now, let's just close the activity
                finish();
            }
        });
    }
}
