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

import com.example.cupcake.Adapter.OrderClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CupcakeOrderActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    EditText EditTextQuantity, EditTextCustomerName, EditTextMobileNumber, EditTextPrice;
    Spinner SpinnerProductName;

    Button ButtonOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupcake_order);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        EditTextCustomerName = findViewById(R.id.TXT_O_CustomerName);
        EditTextQuantity = findViewById(R.id.TXT_O_ProductQuantity);
        EditTextMobileNumber = findViewById(R.id.TXT_O_CUSMobileNumber);
        EditTextPrice = findViewById(R.id.TXT_O_Price);
        SpinnerProductName = findViewById(R.id.SPN_O_ProductName);
        ButtonOrder = findViewById(R.id.BTN_Order);

        // Fetch product names from the database and populate the spinner
        List<String> productNames = dbHelper.getProductNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerProductName.setAdapter(adapter);

        ButtonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextCustomerName.getText().toString().isEmpty() ||
                        EditTextQuantity.getText().toString().isEmpty() ||
                        EditTextMobileNumber.getText().toString().isEmpty() ||
                        EditTextPrice.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                } else {
                    String selectedProduct = SpinnerProductName.getSelectedItem().toString();
                    int total = Integer.parseInt(EditTextPrice.getText().toString()) * Integer.parseInt(EditTextQuantity.getText().toString());
                    String customerName = EditTextCustomerName.getText().toString();
                    String mobileNumber = EditTextMobileNumber.getText().toString();
                    int price = Integer.parseInt(EditTextPrice.getText().toString());
                    int quantity = Integer.parseInt(EditTextQuantity.getText().toString());

                    OrderClass orderClass = new OrderClass(selectedProduct, quantity, price, total, customerName, mobileNumber);

                    long orderId = dbHelper.AddOrder(orderClass);
                    if (orderId > -1) {
                        Toast.makeText(getApplicationContext(), "Order placed successfully! Order ID: " + orderId, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to place order", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
    }
    public void BackClick(View view) {

        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}
