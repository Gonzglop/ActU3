package com.example.android_t5_ej1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Recupero el elemento del layout y defino los valores (array) del listado
        ListView listaOpciones = findViewById(R.id.list);
        //listaOpciones.addFooterView(listaOpciones);
        final String[] datos = {"Opción 1","Opción 2","Opción 3","Opción 4","Opción 5"};

        //Creo el adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);

        //Asigno el adaptador
        listaOpciones.setAdapter(adaptador);

        //Defino el manejador de eventos
        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                //3 opciones de hacerlo

                //Toast.makeText(MainActivity.this, datos[posicion], Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(posicion).toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, adapterView.getAdapter().getItem(posicion).toString(), Toast.LENGTH_SHORT).show();
            }
        });


        //Recupero el elemento del layout y defino los valores (array) del listado
        ListView listaOpciones2 = findViewById(R.id.list2);
        final String[] datos2 = {"Opción 1","Opción 2","Opción 3","Opción 4","Opción 5"};

        //Creo el adaptador
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos2);

        //Asigno el adaptador
        listaOpciones2.setAdapter(adaptador2);

        //Defino el manejador de eventos
        listaOpciones2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                //3 opciones de hacerlo

                //Toast.makeText(MainActivity.this, datos[posicion], Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(posicion).toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, adapterView.getAdapter().getItem(posicion).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}