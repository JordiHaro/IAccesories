package com.example.iaccesories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class ForoFragment extends Fragment {

    private EditText mET_Mensaje;
    private Button mBT_Enviar;
    private FirebaseAuth mAuth;
    private FirebaseUser mUsuariActual;
    private FirebaseDatabase mDataBase = FirebaseDatabase.getInstance("https://iaccesories-7300a-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference mReference = mDataBase.getReference();
    private String nombreUsuario;
    private ListView mLVMensajes;
    private List<Missatge> mListaMensajes = new ArrayList<>();
    private ArrayAdapter<Missatge> mAdapterMensajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_foro, container, false);

        mET_Mensaje = v.findViewById(R.id.ET_Mensaje);
        mBT_Enviar = v.findViewById(R.id.BT_Enviar);
        mAuth = FirebaseAuth.getInstance();
        mUsuariActual = mAuth.getCurrentUser();
        mLVMensajes = v.findViewById(R.id.LV_Mensajes);

        Log.d("--->", mUsuariActual.getEmail());

        BuscarUsuario();

        mBT_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = mET_Mensaje.getText().toString().trim();
                String uid = mReference.push().getKey();

                Missatge missatge = new Missatge(nombreUsuario, mensaje, uid);

                mReference.child("Mensajes").child(uid).setValue(missatge);

                resetCamps();
            }
        });

        return v;
    }

    private void resetCamps() {
        mET_Mensaje.setText("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LlistarMissatges();
    }

    private void LlistarMissatges() {
        if (!isAdded()) {
            return; //
        }

        mReference.child("Mensajes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListaMensajes.clear();

                for (DataSnapshot mensajeActual: snapshot.getChildren()) {
                    Missatge mensaje = mensajeActual.getValue(Missatge.class);
                    mListaMensajes.add(mensaje);
                }

                if (!isAdded()) {
                    return;
                }

                mAdapterMensajes = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, mListaMensajes);
                mLVMensajes.setAdapter(mAdapterMensajes);
                mAdapterMensajes.notifyDataSetChanged();
                mLVMensajes.setSelection(mListaMensajes.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void BuscarUsuario() {
        Query query = mReference.child("usuari").orderByChild("email").equalTo(mUsuariActual.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    nombreUsuario = "" + ds.child("nombreUsuario").getValue();
                }
                Log.d("---<", "Algo" + nombreUsuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
