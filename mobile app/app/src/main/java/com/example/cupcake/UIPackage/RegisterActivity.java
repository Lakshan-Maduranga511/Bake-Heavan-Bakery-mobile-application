package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cupcake.Adapter.UserClass;
import com.example.cupcake.DBClass.DBHelper;
import com.example.cupcake.R;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
  EditText EditTextUserName,EditTextPassword,EditTextConfirmPassword;

  Button ButtonRegister;
  private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        EditTextUserName=(EditText) findViewById(R.id.txt_Username);
        EditTextPassword=(EditText) findViewById(R.id.txt_Password);
        EditTextConfirmPassword=(EditText) findViewById(R.id.txt_ConfirmPassword);
        ButtonRegister=(Button) findViewById(R.id.Register);


        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditTextUserName.getText().toString().isEmpty() ||
                        EditTextPassword.getText().toString().isEmpty() ||
                        EditTextConfirmPassword.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                } else if (EditTextPassword.getText().toString().length() < 3) {
                    Toast.makeText(getApplicationContext(), "Password must be more than 3 Character", Toast.LENGTH_LONG).show();
                } else if (!EditTextPassword.getText().toString().equals(EditTextConfirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_LONG).show();
                } else {
                    dbHelper = new DBHelper(RegisterActivity.this);
                    dbHelper.OpenDB();
                    UserClass userClass = new UserClass(EditTextUserName.getText().toString(), EditTextPassword.getText().toString());
                    if (dbHelper.CreateNewUser(userClass)) {
                        Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "User creation failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
    public void BackClick(View view) {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}