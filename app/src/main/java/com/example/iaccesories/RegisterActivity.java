package com.example.iaccesories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mBregistre;
    private FirebaseUser mUsuariActual;
    private EditText mETemail;
    private EditText mETpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mETemail = findViewById(R.id.ET_email);
        mETpassword = findViewById(R.id.ET_password);
        mBregistre = findViewById(R.id.BT_registe);
        mAuth = FirebaseAuth.getInstance();

        mBregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mETemail.getText().toString();
                String password = mETpassword.getText().toString();


                /*mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful()) {
                            mUsuariActual = mAuth.getCurrentUser();
                        }
                    }
                });*/
            }
        });
    }

    //Es crida quan s'obre la pantalla y la funcio que fa es mirar si hi ha un usuari amb la sessio iniciada
    @Override
    protected void onStart() {
        super.onStart();
        /*mUsuariActual = mAuth.getCurrentUser();
        if(mUsuariActual != null) {
            Intent intent = new Intent(RegisterActivity.this, PantallaPrincipal.class);
            startActivity(intent);
        }*/
    }
}