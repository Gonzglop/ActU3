package com.example.ejemplobasedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos("Línea superior 1", "Linea inferior1"));
        datos.add(new Datos("Línea superior 2", "Linea inferior2"));
        datos.add(new Datos("Línea superior 3", "Linea inferior3"));
        datos.add(new Datos("Línea superior 4", "Linea inferior4"));
        datos.add(new Datos("Línea superior 5", "Linea inferior5"));

        datos.add(new Datos("Consola", R.drawable.consola));
        datos.add(new Datos("Ordenador fijo", R.drawable.ordenador_fijo));
        datos.add(new Datos("Ordenador portátil", R.drawable.ordenador_portatil));
        datos.add(new Datos("Reloj", R.drawable.reloj));
        datos.add(new Datos("Smartphone", R.drawable.smartphone));
        datos.add(new Datos("Tablet", R.drawable.tablet));
        datos.add(new Datos("Televisión", R.drawable.tv));

        final ListView listado = findViewById(R.id.miLista);
        final View miCabecera = getLayoutInflater().inflate(R.layout.cabecera,null);
        listado.addHeaderView(miCabecera);

        Adaptador adaptador = new Adaptador(datos, this);
        listado.setAdapter(adaptador);
    }
}