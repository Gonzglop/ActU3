package com.example.android_t5_ej5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView texto1 = findViewById(R.id.text1);


        //Recupero el elemento del layout y defino los valores (array) del listado
        Spinner listaSpinner = findViewById(R.id.spinner);

        final String[] datosS = {"Stranger Things","A Korean Odyssey","Alquimia de almas","Los Brigerton","Chernobyl","Supernatural", "Cobra Kai", "Navidad en casa", "Desaparecido", "Winx","Lucifer"};

        //Creo el adaptador
        ArrayAdapter<String> adaptadorS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datosS);

        //Asigno el adaptador
        listaSpinner.setAdapter(adaptadorS);

        //Defino el manejador de eventos

        listaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                texto1.setText(adapterView.getAdapter().getItem(posicion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}