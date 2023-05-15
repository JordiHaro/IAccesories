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
                switch (item.getItemId()) {
                    case R.id.Inicio:
                        Log.d("--->","Entra?");
                        fragment = new InicioFragment();
                        //Com carregar els fragments
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        return true;
                    case R.id.Noticias:
                        Log.d("--->","EntraNoticias?");
                        fragment = new NoticiasFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        return true;
                    case R.id.Foro:
                        Log.d("--->","EntraForo?");
                        fragment = new ForoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        return true;
                    case R.id.Contacto:
                        Log.d("--->","EntraContacto?");
                        fragment = new ContactoFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                        return true;
                }

                return false;
            }
        });
    }
}