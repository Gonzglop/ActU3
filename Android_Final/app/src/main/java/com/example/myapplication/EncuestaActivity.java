package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EncuestaActivity extends AppCompatActivity {
    private ArrayList<Opcion> datos; // Guardamos las 32 opcion del listado
    private RecyclerView recView; // Lista tipo RecyclerView
    private Button btnInsertar;
    private Button btnBorrar;
    private Button btnMover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.encuesta);
        // Buscamos el layout del RecyclerView
        recView = (RecyclerView) findViewById(R.id.recView);
        // Indicamos que el tamaño del RecyclerView no cambia
        recView.setHasFixedSize(true);
        datos = new ArrayList<Opcion>();
        // Definimos las 32 opcion
        for (int i=1; i<=15; i++){
            if (i%2 == 0){
                datos.add(new Opcion("Bocadillo " + i,"1," + i,
                        R.drawable.logo80px));
            }
            else{
                datos.add(new Opcion("Bebida " + i, "2," + i,
                        R.drawable.logo80px));
            }
        }
        // Creamos el adaptador que se usa para mostrar las opcion del listado
        final AdaptadorOpciones adaptador = new AdaptadorOpciones(datos);
        // Definimos el evento onClick
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Has hecho clic en " +
                        datos.get(recView.getChildAdapterPosition(view)).getTitulo(),Toast.LENGTH_SHORT).show();
            }
        });
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recView.setItemAnimator(new DefaultItemAnimator());
        /*
        //Definimos los eventos onClic
        btnInsertar = (Button) findViewById(R.id.BtnInsertar);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datos.add(1,new Opcion("Nueva opción","Subtítulo nueva opción",R.drawable.logo));
                        adaptador.notifyItemInserted(1);
            }
        });
        btnBorrar = (Button) findViewById(R.id.BtnBorrar);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datos.size()<2) return;
                datos.remove(1);
                adaptador.notifyItemRemoved(1);
            }
        });
        btnMover = (Button) findViewById(R.id.BtnMover);
        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Opcion aux = datos.get(1);
                datos.set(1, datos.get(2));
                datos.set(2,aux);
                adaptador.notifyItemMoved(1,2);
            }
        });*/
    }


}