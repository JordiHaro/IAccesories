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
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new InicioFragment()).commit();
                        return true;
                    case R.id.Noticias:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new NoticiasFragment()).commit();
                        return true;
                    case R.id.Foro:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ForoFragment()).commit();
                        return true;
                    case R.id.Contacto:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContactoFragment()).commit();
                        return true;
                }


                return false;
            }
        });
    }
}