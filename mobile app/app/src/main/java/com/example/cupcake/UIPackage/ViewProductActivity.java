package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cupcake.Adapter.ProductAdapter;
import com.example.cupcake.Adapter.ProductClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;

import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        dbHelper = new DBHelper(this);
         dbHelper.OpenDB();

        String selectedCategory = getIntent().getStringExtra("selectedCategory");

        recyclerView = findViewById(R.id.RV_ProductDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<ProductClass> products = dbHelper.SearchProduct(selectedCategory);


        ProductAdapter adapter = new ProductAdapter(products);

        recyclerView.setAdapter(adapter);


        Toast.makeText(this, "Selected Category: " + selectedCategory, Toast.LENGTH_SHORT).show();
    }
    public void BackClick(View view) {

        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}
