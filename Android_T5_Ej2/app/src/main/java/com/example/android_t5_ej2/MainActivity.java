package com.example.android_t5_ej2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

        ListView listaOpciones = findViewById(R.id.list);
        TextView texto1 = findViewById(R.id.text1);

        final String[] datos = {"Stranger Things","A Korean Odyssey","Alquimia de almas","Los Brigerton","Chernobyl","Supernatural", "Cobra Kai", "Navidad en casa", "Desaparecido", "Winx","Lucifer"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);

        listaOpciones.setAdapter(adaptador);

        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                texto1.setText(adapterView.getAdapter().getItem(posicion).toString());
            }
        });

    }
}