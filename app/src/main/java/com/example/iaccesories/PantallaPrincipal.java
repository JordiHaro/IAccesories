package com.example.iaccesories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PantallaPrincipal extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment  fragment;
    InicioFragment inicioFragment = new InicioFragment();
    NoticiasFragment noticiasFragment = new NoticiasFragment();
    ForoFragment foroFragment = new ForoFragment();
    ContactoFragment contactoFragment = new ContactoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        bottomNavigationView = findViewById(R.id.bottom_navegation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, inicioFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Log.d("---", ""+ item.getItemId());
                switch (item.getItemId()) {
                    case R.id.Inicio:
                        fragment = new InicioFragment();
                        return true;
                    case R.id.Noticias:
                        fragment = new NoticiasFragment();
                        return true;
                    case R.id.Foro:
                        fragment = new ForoFragment();
                        return true;
                    case R.id.Contacto:
                        fragment = new ContactoFragment();
                        return true;
                }

                return false;
            }
        });
    }
}