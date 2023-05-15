package com.example.iaccesories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForoFragment extends Fragment {

    private EditText mET_Mensaje;
    private Button mBT_Enviar;
    private FirebaseAuth mAuth;
    private FirebaseUser mUsuariActual;
    private FirebaseDatabase mDataBase = FirebaseDatabase.getInstance("https://iaccesories-7300a-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference mReference = mDataBase.getReference();
    private String nombreUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_foro, container, false);

        mET_Mensaje = v.findViewById(R.id.ET_Mensaje);
        mBT_Enviar = v.findViewById(R.id.BT_Enviar);
        mAuth = FirebaseAuth.getInstance();
        mUsuariActual = mAuth.getCurrentUser();

        Log.d("--->", mUsuariActual.getEmail());

        BuscarUsuario();

        mBT_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Guardar el missatge a la bbdd
                //El missatge te el missatge que escriu y qui l'escriu.

            }
        });

        return v;
    }

    private void BuscarUsuario() {

        Query query = mReference.child("usuari").orderByChild("email").equalTo(mUsuariActual.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    nombreUsuario = "" + ds.child("nombreUsuario").getValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}