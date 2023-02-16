package com.example.tema7ejercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero los elementos del layout
        final Button btnPreferencias = findViewById(R.id.btnDefinir);
        final Button btnConsultar = findViewById(R.id.btnRecuperar);

        // Abro la ventana de configuración de preferencias
        btnPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpcionesActivity.class));
            }
        });

        // Muestro las preferencias configuradas
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                Log.i("Preferences", "\t org.belen.t7e2pa\t  SO único: " + pref.getBoolean("opcion1",false));
                Log.i("Preferences", "\t org.belen.t7e2pa\t  SO: " + pref.getString("opcion2",""));
                Log.i("Preferences", "\t org.belen.t7e2pa\t  Versión: " + pref.getString("opcion3",""));

            }
        });
    }
}