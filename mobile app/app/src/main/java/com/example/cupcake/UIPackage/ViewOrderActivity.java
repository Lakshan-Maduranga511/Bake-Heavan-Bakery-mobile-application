package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupcake.Adapter.OrderClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {

      EditText EditTextOrderId;

      Button ButtonOrderSubmit;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);


        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        EditTextOrderId=findViewById(R.id.TXT_D_OID);
        ButtonOrderSubmit=findViewById(R.id.btn_D_Submit);


        ButtonOrderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextOrderId.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please input oder ID", Toast.LENGTH_LONG).show();
                } else {
                    String OrderID = EditTextOrderId.getText().toString();
                    ArrayList<OrderClass> OrderList = dbHelper.SearchOrder(OrderID);
                    if (OrderList.size() > 0) {
                        // Assuming there's only one order for a given order ID
                        OrderClass order = OrderList.get(0);


                        new AlertDialog.Builder(ViewOrderActivity.this)
                                .setTitle("Order Details")
                                .setMessage("Product Name: " + order.getProductName() + "\n" +
                                        "Quantity: " + order.getQuantity() + "\n" +
                                        "Price: "+"Rs." + order.getPrice() + "\n" +
                                        "Total Price: "+"Rs." + order.getTotal() + "\n" +
                                        "Customer Name: " + order.getCustomerName() + "\n" +
                                        "Mobile Number: " + order.getMobileNumber() + "\n")
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No order found for the given ID", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }
}