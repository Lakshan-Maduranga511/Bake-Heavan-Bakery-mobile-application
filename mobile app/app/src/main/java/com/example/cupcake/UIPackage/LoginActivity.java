package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cupcake.Adapter.UserClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText EditTextUserName, EditTextPassword;
    Button btnLogin;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditTextUserName = (EditText) findViewById(R.id.txt_L_Username);
        EditTextPassword = (EditText) findViewById(R.id.txt_L_Password);
        btnLogin = (Button) findViewById(R.id.btn_L_Login);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextUserName.getText().toString().isEmpty() || EditTextPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                } else if (EditTextUserName.getText().toString().equals("Admin") && EditTextPassword.getText().toString().equals("1234")) {
                    Toast.makeText(getApplicationContext(), "Successfully Login.Welcome to admin menu", Toast.LENGTH_LONG).show();
                    Intent intentAdmin = new Intent(LoginActivity.this, AdminMenuActivity.class);
                    startActivity(intentAdmin);

                } else {
                    ArrayList<UserClass> userDetails = dbHelper.ValidLogin(EditTextUserName.getText().toString(), EditTextPassword.getText().toString());
                    if (userDetails.size() != 0) {
                        UserClass user = userDetails.get(0);
                        String UserName = user.getUserName();//Admin
                        Toast.makeText(getApplicationContext(), "Login Successful. Welcome " + UserName, Toast.LENGTH_LONG).show();

                        // Assuming you have retrieved the customer name and stored it in a variable named 'customerName'
                        Intent intentCustomer = new Intent(LoginActivity.this, UserMenuActivity.class);
                        intentCustomer.putExtra("customerName", UserName);
                        startActivity(intentCustomer);

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid user please try again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        }
    public void onRegisterClick(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}


