package com.example.iaccesories;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ContactoFragment extends Fragment {

    private EditText etSubject;
    private EditText etEmail;
    private EditText etMensaje;
    private Button btnEnviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacto, container, false);

        etEmail = v.findViewById(R.id.edit_email);
        etSubject = v.findViewById(R.id.edit_subject);
        etMensaje = v.findViewById(R.id.edit_message);
        btnEnviar = v.findViewById(R.id.btn_send);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarCorreo();
            }
        });

        return v;
    }

    private void enviarCorreo() {
        String email = etEmail.getText().toString();
        String subject = etSubject.getText().toString();
        String mensaje = etMensaje.getText().toString();

        // Crea el Intent para enviar correo electrónico
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"iaccesoriesroca@gmail.com"});  // Reemplaza con tu dirección de correo destino
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mensaje de contacto");
        intent.putExtra(Intent.EXTRA_TEXT, "Email: " + email + "\nSujeto: " + subject + "\nMensaje: " + mensaje);

        // Verifica si hay una aplicación de correo electrónico instalada
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Enviar correo electrónico"));
            resetearCampos();
        } else {
            Toast.makeText(getActivity(), "No se encontró una aplicación de correo electrónico instalada.", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetearCampos() {
        etEmail.setText("");
        etSubject.setText("");
        etMensaje.setText("");
    }
}