package com.example.t4_ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void mandar_mensaje(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"¿A qué hora quedamos?");
        startActivity(intent);
    }
    public void abrirPagina(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.iesbelen.org/"));
        startActivity(intent);
    }
    public void llamarTelefono(View view){
        Intent intent = new
                Intent(Intent.ACTION_DIAL,Uri.parse("tel:000000000"));
        startActivity(intent);
    }
    public void verMapa(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:36.695804,-4.457127?z=18"));
        startActivity(intent);
    }
    public void tomarFoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    public void mandarCorreo(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"asunto");
        intent.putExtra(Intent.EXTRA_TEXT,"texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new
                String[]{"rbaebar@g.educaand.es"});
        startActivity(intent);
    }
    public void streetView(View view){
        Intent intent = new
                Intent(Intent.ACTION_VIEW,Uri.parse("google.streetview:cbll=38.996766, -0.1652696&cbp=0,250,0,0,0"));
        //cbll=latitud,longitud&cbp=0,azimut,0,zoom,altura
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mensaje=findViewById(R.id.btnMensaje);
        Button web=findViewById(R.id.btnWeb);
        Button telefono=findViewById(R.id.btnTelefono);
        Button mapa=findViewById(R.id.btnMapa);
        Button foto=findViewById(R.id.btnFoto);
        Button correo=findViewById(R.id.btnCorreo);
        Button street=findViewById(R.id.btnStreet);


        mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandar_mensaje(view);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPagina(view);
            }
        });
        telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarTelefono(view);
            }
        });
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verMapa(view);
            }
        });
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFoto(view);
            }
        });
        correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarCorreo(view);
            }
        });
        street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                streetView(view);
            }
        });




    }
}