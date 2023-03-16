package com.example.iaccesories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView mTVregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVregister = findViewById(R.id.TV_register);

        mTVregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this es on es trobem y class es per donar especificacio de una clase
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}