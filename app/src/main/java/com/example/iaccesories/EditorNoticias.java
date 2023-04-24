package com.example.iaccesories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditorNoticias extends AppCompatActivity {

    private EditText ETNombre;
    private EditText ETDescripcion;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private Button BTAñadirNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_noticias);

        ETNombre = findViewById(R.id.ET_NombreNoticia);
        ETDescripcion = findViewById(R.id.ET_Descripcion);
        BTAñadirNoticia = findViewById(R.id.BT_AñadirNoticia);

        mDatabase = FirebaseDatabase.getInstance("https://iaccesories-7300a-default-rtdb.europe-west1.firebasedatabase.app/");
        mReference = mDatabase.getReference();


        BTAñadirNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nombre = ETNombre.getText().toString().trim();
                String Descripcion = ETDescripcion.getText().toString().trim();
                String uid = mReference.push().getKey();//UUID.randomUUID().toString();//

                Noticia noticia = new Noticia(Nombre, Descripcion, uid);

                Log.d("---*", noticia.toString());

                mReference.child("Noticias").child(uid).setValue(noticia);

                ResetCamps();
            }
        });
    }

    private void ResetCamps() {

        ETNombre.setText("");
        ETDescripcion.setText("");
    }
}