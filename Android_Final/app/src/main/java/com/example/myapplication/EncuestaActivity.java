package com.example.myapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EncuestaActivity extends AppCompatActivity {
    private ArrayList<Opcion> datos; // Guardamos las opciones del listado
    private RecyclerView recView; // Lista tipo RecyclerView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.encuesta);
        // Buscamos el layout del RecyclerView
        recView = (RecyclerView) findViewById(R.id.recView);
        // Indicamos que el tamaño del RecyclerView no cambia
        recView.setHasFixedSize(true);
        datos = new ArrayList<Opcion>();
        // Definimos las opciones
        datos.add(new Opcion("Pitufo jamón","1,20€", R.drawable.logo80px));
        datos.add(new Opcion("Pitufo queso","1,20€", R.drawable.logo80px));
        datos.add(new Opcion("Pitufo mixto","1,40€", R.drawable.logo80px));
        datos.add(new Opcion("Pitufo jamón serrano","1,50€", R.drawable.logo80px));
        datos.add(new Opcion("Pitufo catalana","1,80€", R.drawable.logo80px));

        datos.add(new Opcion("Coca-Cola","1,00€", R.drawable.logo80px));
        datos.add(new Opcion("Fanta Naranja","1,00€", R.drawable.logo80px));
        datos.add(new Opcion("Aquarius","1,20€", R.drawable.logo80px));
        datos.add(new Opcion("Café","1,20€", R.drawable.logo80px));
        datos.add(new Opcion("Té","1,20€", R.drawable.logo80px));

/*
        for (int i = 1; i <= 15; i++) {
            datos.add(new Opcion("Bocadillo " + i, "2," + i + "€", R.drawable.logo80px));
        }

        for (int i = 1; i <= 15; i++) {
            datos.add(new Opcion("Bebida " + i, "1," + i + "€", R.drawable.logo80px));
        }

 */
        // Creamos el adaptador que se usa para mostrar las opcion del listado
        final AdaptadorOpciones adaptador = new AdaptadorOpciones(datos);
        // Definimos el evento onClick
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Has añadido al carrito: " +
                        datos.get(recView.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        switch (info.position){
            case 0:
                inflater.inflate(R.menu.menu_contextual,menu);
                return;
        }
    }


}