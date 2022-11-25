package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        final String[] datos = new String[]{"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);

        ListView listaOpciones = findViewById(R.id.miLista);
        //registerForContextMenu((View) listaOpciones.getItemAtPosition(0));

        listaOpciones.setAdapter(adaptador);
        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

            }
        });

        GridView gridOpciones = findViewById(R.id.miGrid);
        gridOpciones.setAdapter(adaptador);
        gridOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

            }
        });

        Spinner spinnerOpciones = (Spinner) findViewById(R.id.miSpinner);
        spinnerOpciones.setAdapter(adaptador);
        spinnerOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual,menu);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        final TextView etiqueta = (TextView) findViewById(R.id.lbl1);
        ListView listaOpciones = findViewById(R.id.miLista);
        GridView gridOpciones = findViewById(R.id.miGrid);
        Spinner spinnerOpciones = (Spinner) findViewById(R.id.miSpinner);



        switch (item.getItemId()){

            case R.id.Mn1:
                etiqueta.setText("");
                listaOpciones.setAlpha(0);
                gridOpciones.setAlpha(0);
                spinnerOpciones.setAlpha(0);
                return true;

            case R.id.Mn2:
                etiqueta.setText("");
                listaOpciones.setAlpha(0);
                gridOpciones.setAlpha(0);
                spinnerOpciones.setAlpha(0);
            return true;

            case R.id.MnOp1:
                etiqueta.setText("Ejecutando intent explícito");
                Intent actInicio = new Intent(MainActivity.this,IntentExp.class);
                startActivity(actInicio);

                return true;

            case R.id.MnOp2:
                etiqueta.setText("Enviando correo");

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"examen t5");
                intent.putExtra(Intent.EXTRA_TEXT,"Gonzalo López. Intent implícito OK");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"rbaebar562@g.educaand.es"});
                startActivity(intent);

                return true;


            case R.id.MnM1:
                etiqueta.setText("Creando lista con ListView");
                listaOpciones.setAlpha(1);
                gridOpciones.setAlpha(0);
                spinnerOpciones.setAlpha(0);

                registerForContextMenu(listaOpciones);

                return true;

            case R.id.MnM2:
                etiqueta.setText("Creando lista con GridView");
                listaOpciones.setAlpha(0);
                gridOpciones.setAlpha(1);
                spinnerOpciones.setAlpha(0);
                return true;

            case R.id.MnM3:
                etiqueta.setText("Creando lista con Spinner");
                listaOpciones.setAlpha(0);
                gridOpciones.setAlpha(0);
                spinnerOpciones.setAlpha(1);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}