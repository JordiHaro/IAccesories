package com.example.iaccesories;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticiasFragment extends Fragment {

    private Button mBTEditorNoticias;
    private FirebaseAuth mAuth;
    private FirebaseUser mUsuariActual;
    private FirebaseDatabase mDataBase = FirebaseDatabase.getInstance("https://iaccesories-7300a-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference mReference = mDataBase.getReference();
    private ListView mLvCarta;
    private List<Noticia> mListaNoticias = new ArrayList<>();
    private ArrayAdapter<Noticia> mAdapterNoticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_noticias, container, false);

        Log.d("---", "Dins fragment noticias");
        mBTEditorNoticias = v.findViewById(R.id.BT_EditorNoticias);
        mAuth = FirebaseAuth.getInstance();
        mUsuariActual = mAuth.getCurrentUser();
        mLvCarta = v.findViewById(R.id.LV_Carta);

        Log.d("--->", mUsuariActual.getEmail());

        mBTEditorNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditorNoticias.class);
                startActivity(intent);
            }
        });

        BuscarUsuariActual();
        ListarNoticias();



        //Buscar el usuari a la base de dades (saber si el seu camp es admin o usuari). Si es admin mostrar el boto si es usuari no
        //mBTEditorNoticias.setVisibility(View.GONE);
        return v;

    }

    private void ListarNoticias() {

        mReference.child("Noticias").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("----",snapshot.toString());

                mListaNoticias.clear();

                for (DataSnapshot noticiaActual: snapshot.getChildren()) {

                    Noticia noticia = noticiaActual.getValue(Noticia.class);
                    mListaNoticias.add(noticia);
                    Log.d("----***",noticia.toString());
                }

                mAdapterNoticias = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListaNoticias);
                mLvCarta.setAdapter(mAdapterNoticias);

        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void BuscarUsuariActual() {

        //Query query = mDBReference.orderByChild("UID").equalTo(mUsuariActual.getUid());
        Query query = mReference.child("usuari").orderByChild("email").equalTo(mUsuariActual.getEmail());

        //Log.d("----", "BuscarUsuariActual: " + mUsuariActual.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String email = "" + ds.child("email").getValue();
                    String tipoUsuario = "" + ds.child("tipoUsuario").getValue();

                    if (tipoUsuario.equals("admin")) {
                        mBTEditorNoticias.setVisibility(View.VISIBLE);
                    } else {
                        mBTEditorNoticias.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}