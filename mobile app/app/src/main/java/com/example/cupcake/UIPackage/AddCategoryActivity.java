package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cupcake.Adapter.CategoryClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

public class AddCategoryActivity extends AppCompatActivity {
    EditText editTextNewCategoryID, editTextNewCategoryName;
    Button buttonCategorySubmit;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        editTextNewCategoryID = findViewById(R.id.txt_I_Category_ID);
        editTextNewCategoryName = findViewById(R.id.txt_I_CategoryName);
        buttonCategorySubmit = findViewById(R.id.btn_I_Submit);

        buttonCategorySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNewCategoryID.getText().toString().isEmpty() ||
                        editTextNewCategoryName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                } else {
                    CategoryClass categoryClass = new CategoryClass(
                            editTextNewCategoryID.getText().toString(),
                            editTextNewCategoryName.getText().toString()
                    );

                    if (dbHelper.CreateNewCategory(categoryClass)) {
                        Toast.makeText(getApplicationContext(), "New Category inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
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
