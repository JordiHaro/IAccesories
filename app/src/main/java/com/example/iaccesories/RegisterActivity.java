package com.example.iaccesories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

                // =====================================================================
                // He tret el "this" del OnCompleteListener.
                // Dins del codi onComplete, és on heu de fer les accions que voleu,
                // per exemple, mostrar un Toast dient que s'ha registrat correctament,
                // i redirigint a l'usuari a la pantalla principal.
                // Com a acció important, també haureu de guardar l'usuari a
                // FirebaseDatabase, ja que el Authentication, només guarda
                // email i password. En l'objecte Usuari de la Database, hi podeu
                // guardar el id (que l'haurà generat el FirebaseAuthenticator),
                // el nom, el email, i les altres dades que cregueu oportunes.
                // =====================================================================

                // En la pantalla de login, serà semblant, però fent servir el
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Usuari usuari = new Usuari();
                                // Guardar l'usuari en el currentUser.
                                // Guardar usuari a FirebaseDatabase.
                                // Mostrar Toast.
                                // Obrir Activity Principal.
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                // Mostrar Toast dient que hi ha hagut un
                                //      error connectant en la base de dades,
                                //      i que ho tornin a provar.
                            }
                        });
            }
        });
    }

    //Es crida quan s'obre la pantalla y la funcio que fa es mirar si hi ha un usuari amb la sessio iniciada
    @Override
    protected void onStart() {
        super.onStart();
        mUsuariActual = mAuth.getCurrentUser();
        if(mUsuariActual != null) {
            //Intent intent = new Intent(RegisterActivity.this, PantallaPrincipal.class);
            //startActivity(intent);
        }
    }
}