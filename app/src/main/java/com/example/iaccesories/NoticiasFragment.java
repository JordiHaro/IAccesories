package com.example.iaccesories;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticiasFragment extends Fragment {

    private Button mBTEditorNoticias;
    private FirebaseAuth mAuth;
    private FirebaseUser mUsuariActual;
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);

        mBTEditorNoticias = v.findViewById(R.id.BT_EditorNoticias);
        mAuth = FirebaseAuth.getInstance();
        mUsuariActual = mAuth.getCurrentUser();

        Log.d("--->", mUsuariActual.getEmail());

        mBTEditorNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditorNoticias.class);
                startActivity(intent);
            }
        });

        BuscarUsuariActual();



        //Buscar el usuari a la base de dades (saber si el seu camp es admin o usuari). Si es admin mostrar el boto si es usuari no
        //mBTEditorNoticias.setVisibility(View.GONE);
        return v;

    }

    public void BuscarUsuariActual() {



    }
}