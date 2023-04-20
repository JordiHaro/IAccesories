package com.example.iaccesories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button mBTAcceder;
    private TextView mTVregister;
    private FirebaseAuth mAuth;
    private EditText mETemail;
    private EditText mETpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mETemail = findViewById(R.id.ET_email);
        mETpassword = findViewById(R.id.ET_password);
        mTVregister = findViewById(R.id.TV_register);
        mAuth = FirebaseAuth.getInstance();
        mBTAcceder = findViewById(R.id.BT_acceder);

        mBTAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mETemail.getText().toString();
                String password = mETpassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, PantallaPrincipal.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "No estas registrado en nuestra base de datos", Toast.LENGTH_LONG).show();
                        }
                    }

                });



            }
        });

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