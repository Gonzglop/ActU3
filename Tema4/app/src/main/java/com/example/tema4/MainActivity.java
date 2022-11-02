package com.example.tema4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

        if(savedInstanceState!=null){
            //Recuperamos una variable que almacena con el estado
            Toast.makeText(this, "Evento onCreate(). Se recupera variable almacenada: "
                    + savedInstanceState.getString("variable_almacenada"), Toast.LENGTH_SHORT).show();
        }
    }

    //guardamos el estado de la actividad
    @Override
    protected void onSaveInstanceState(Bundle estado){
        super.onSaveInstanceState(estado);
        //añadimos una variable que se almacena con el estado
        estado.putString("variable_almacenada","Texto guardado");

        Toast.makeText(this, "El estado de la actividad se ha guardado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "El sistema ha terminado la actividad", Toast.LENGTH_SHORT).show();

        //creo un intent para abrir Google
        Intent ejemplo = new Intent(Intent.ACTION_VIEW);
        ejemplo.setData(Uri.parse("http://www.google.es"));
        startActivity(ejemplo);
    }
    //Dev-tools --> Opciones de desarrollo

}