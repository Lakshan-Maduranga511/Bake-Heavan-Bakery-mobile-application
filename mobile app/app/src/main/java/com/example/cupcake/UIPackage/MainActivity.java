package com.example.cupcake.UIPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cupcake.R;

public class MainActivity extends AppCompatActivity {
     Button btnlogin,btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin=(Button) findViewById(R.id.btn_login);
        btnregister=(Button) findViewById(R.id.btn_register);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(intentLogin);
            }

        });

       btnregister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentRegister = new Intent(MainActivity.this,
                       RegisterActivity.class);
               startActivity(intentRegister);
           }
       });

    }
}