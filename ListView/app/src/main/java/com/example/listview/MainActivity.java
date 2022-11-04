package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

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
        GridView listado = findViewById(R.id.grid);
        final String[] datosG = {"Elemento 1","Elemento 2","Elemento 3","Elemento 4","Elemento 5"};

        //Creo el adaptador
        ArrayAdapter<String> adaptadorG = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datosG);

        //Asigno el adaptador
        listado.setAdapter(adaptadorG);

        //Defino el manejador de eventos
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                //3 opciones de hacerlo

                //Toast.makeText(MainActivity.this, datosG[posicion], Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(posicion).toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, adapterView.getAdapter().getItem(posicion).toString(), Toast.LENGTH_SHORT).show();
            }
        });


        //Recupero el elemento del layout y defino los valores (array) del listado
        Spinner listaSpinner = findViewById(R.id.spinner);
        final String[] datosS = {"Sección 1","Sección 2","Sección 3","Sección 4","Sección 5"};

        //Creo el adaptador
        ArrayAdapter<String> adaptadorS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datosS);

        //Asigno el adaptador
        listaSpinner.setAdapter(adaptadorS);

        //Defino el manejador de eventos

        listaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                //3 opciones de hacerlo

                //Toast.makeText(MainActivity.this, datosS[posicion], Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(posicion).toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, adapterView.getAdapter().getItem(posicion).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}