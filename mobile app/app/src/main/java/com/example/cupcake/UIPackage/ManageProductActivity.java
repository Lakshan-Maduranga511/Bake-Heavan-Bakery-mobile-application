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

import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ManageProductActivity extends AppCompatActivity {
    EditText EditTextDProductID,EditTextDProductName;

    Button ButtonDeleteProduct;

    EditText EditTextEProductID,EditTextEProductname,EdittextEProductDescription,EdittextEProductPrice,EdittextEProductQuantity;

    Spinner EProductCategory;

    Button ButtonEditProduct;

    private  Spinner GetCategory;

    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

       GetCategory = findViewById(R.id.spn_M_ECategory);
        dbHelper = new DBHelper(this);

        dbHelper.OpenDB();

        List<String> categoryNames = dbHelper.getcategorynames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        GetCategory.setAdapter(adapter);
         EditTextDProductID=findViewById(R.id.txt_M_DPID);
         EditTextDProductName=findViewById(R.id.txt_M_DPName);

         EditTextEProductID=findViewById(R.id.txt_M_EPID);
         EditTextEProductname=findViewById(R.id.txt_M_EPName);
         EdittextEProductDescription=findViewById(R.id.txt_M_EDescription);
         EdittextEProductPrice=findViewById(R.id.txt_M_EPrice);
         EdittextEProductQuantity=findViewById(R.id.txt_M_EQuantity);
         EProductCategory=findViewById(R.id.spn_M_ECategory);

        ButtonDeleteProduct = findViewById(R.id.btn_M_DProduct);
        ButtonEditProduct = findViewById(R.id.btn_M_EditProduct);

        ButtonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EditTextDProductID.getText().toString().isEmpty() || EditTextDProductName.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();



                }
                else {
                    String ProductID=EditTextDProductID.getText().toString();
                    String ProductName=EditTextDProductName.getText().toString();

                  if (dbHelper.deleteProduct(ProductID,ProductName)){
                      Toast.makeText(getApplicationContext(), "Prduct delete successfully", Toast.LENGTH_LONG).show();
                  }else {

                      Toast.makeText(getApplicationContext(), "Product delete failed.!", Toast.LENGTH_LONG).show();
                  }
                }
            }
        });
        ButtonEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextEProductID.getText().toString().isEmpty() || EditTextEProductname.getText().toString().isEmpty() ||
                        EdittextEProductQuantity.getText().toString().isEmpty() || EdittextEProductPrice.getText().toString().isEmpty() ||
                        EProductCategory.getSelectedItem().toString().isEmpty() || EdittextEProductDescription.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                } else {
                    String ProductID = EditTextEProductID.getText().toString();
                    String ProductName = EditTextEProductname.getText().toString();
                    String ProductQuantity = EdittextEProductQuantity.getText().toString();
                    String ProductPrice = EdittextEProductPrice.getText().toString();
                    String ProductCategory = EProductCategory.getSelectedItem().toString();
                    String ProductDescription =EdittextEProductDescription.getText().toString();

                    if (dbHelper.editProduct(ProductID, ProductName, ProductDescription, ProductPrice, ProductQuantity, ProductCategory)) {
                        Toast.makeText(getApplicationContext(), "Product edited successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Product edit fail", Toast.LENGTH_LONG).show();
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