package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupcake.Adapter.ProductClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private Spinner categorySpinner;
    private DBHelper dbHelper;

    EditText EditTextNewProductID,EditTextNewProductname,EditTextProductPrice,EditTextProductDescription,EditTextProductQuantity;
    Spinner SpinnerAddCategory;

    Button ButtonAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);




        categorySpinner = findViewById(R.id.spn_I_AddCategory);
        dbHelper = new DBHelper(this);


        dbHelper.OpenDB();


        List<String> categoryNames = dbHelper.getcategorynames();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        categorySpinner.setAdapter(adapter);

        EditTextNewProductID=findViewById(R.id.txt_I_PID);
        EditTextNewProductname=findViewById(R.id.txt_I_PName);
        EditTextProductDescription=findViewById(R.id.txt_I_PDescription);
        EditTextProductPrice=findViewById(R.id.txt_I_PPrice);
        EditTextProductQuantity=findViewById(R.id.txt_I_PQ);
        SpinnerAddCategory=findViewById(R.id.spn_I_AddCategory);
        ButtonAddProduct=findViewById(R.id.btn_I_Product);

        ButtonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextNewProductID.getText().toString().isEmpty() ||
                        EditTextNewProductname.getText().toString().isEmpty() ||
                        EditTextProductDescription.getText().toString().isEmpty() ||
                        EditTextProductPrice.getText().toString().isEmpty() ||
                        EditTextProductQuantity.getText().toString().isEmpty()  ||
                        SpinnerAddCategory.getSelectedItem().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();

                } else {
                    String selectedCategory = SpinnerAddCategory.getSelectedItem().toString();
                    ProductClass productClass = new ProductClass(
                            EditTextNewProductID.getText().toString(),
                            EditTextNewProductname.getText().toString(),
                            EditTextProductDescription.getText().toString(),
                            selectedCategory,
                            Float.parseFloat(EditTextProductPrice.getText().toString()),
                            Integer.parseInt(EditTextProductQuantity.getText().toString())
                    );


                    if (dbHelper.AddNewProduct(productClass)) {
                        Toast.makeText(getApplicationContext(), "New Product inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Product Insertion Failed", Toast.LENGTH_LONG).show();
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

