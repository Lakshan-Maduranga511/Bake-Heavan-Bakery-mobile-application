package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cupcake.Adapter.CategoryAdapter;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;

import java.util.List;

public class ViewCategoriesActivity extends AppCompatActivity {
    ListView CategoryList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_categories);

        CategoryList = findViewById(R.id.list_V_Categories);
        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        List<String> categoryNames = dbHelper.getcategorynames();

        CategoryAdapter adapter = new CategoryAdapter(this, categoryNames);
        CategoryList.setAdapter(adapter);

        CategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected category name
                String selectedCategory = categoryNames.get(position);

                // Pass the selected category name to the next activity
                Intent intent = new Intent(ViewCategoriesActivity.this, ViewProductActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);
            }
        });
    }
    public void BackClick(View view) {

        Intent intent = new Intent(this, AdminMenuActivity.class);
        startActivity(intent);
    }
}

