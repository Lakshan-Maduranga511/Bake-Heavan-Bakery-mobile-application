package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cupcake.Adapter.ProductClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ManageCatageryActivity extends AppCompatActivity {

    Button ButtonDelete, ButtonEdit;

    Spinner GetCategorySpinner,ShowCategorySpinner;

     EditText EditTextNewCategory;
    private Spinner ManageCategorySpinner;

    private Spinner ManageCategory2Spinner;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_catagery);
        ManageCategorySpinner = findViewById(R.id.spn_M_Category);
        EditTextNewCategory = findViewById(R.id.txt_M_NewCategoryName);
        ButtonDelete=findViewById(R.id.btn_M_Delete);
        ButtonEdit=findViewById(R.id.btn_M_Edit);

        dbHelper = new DBHelper(this);

        dbHelper.OpenDB();

        List<String> categoryNames = dbHelper.getcategorynames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ManageCategorySpinner.setAdapter(adapter);

        GetCategorySpinner = findViewById(R.id.spn_M_Category);


        ManageCategory2Spinner=findViewById(R.id.spn_M_Category2);
        dbHelper  = new DBHelper(this);
        dbHelper.OpenDB();
        List<String> categoryNames1 = dbHelper.getcategorynames();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         ManageCategory2Spinner.setAdapter(adapter1);


         ShowCategorySpinner =findViewById(R.id.spn_M_Category2);



        ButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GetCategorySpinner.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select category", Toast.LENGTH_LONG).show();
                } else {
                    String selectedCategory = GetCategorySpinner.getSelectedItem().toString();
                    if (dbHelper.DeleteCategory(selectedCategory)) {
                        Toast.makeText(getApplicationContext(), "Category deleted successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to delete category", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        ButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShowCategorySpinner.getSelectedItem().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select category", Toast.LENGTH_LONG).show();
                } else {
                    String selectedCategory = ShowCategorySpinner.getSelectedItem().toString();
                    String newCategory = EditTextNewCategory.getText().toString().trim();


                        if (dbHelper.editCategory(selectedCategory, newCategory)) {

                            Toast.makeText(getApplicationContext(), "Category Edited successfully", Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(getApplicationContext(), "Failed to edit category", Toast.LENGTH_LONG).show();
                        }

                }
            }
        });

    }
    public void BackClick(View view) {

        Intent intent = new Intent(this, AdminMenuActivity.class);
        startActivity(intent);
    }
}
