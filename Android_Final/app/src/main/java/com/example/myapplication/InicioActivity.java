package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        final Button boton1 = (Button) findViewById(R.id.bton5);
        final Button boton2 = (Button) findViewById(R.id.bton2);
        final Button boton3 = (Button) findViewById(R.id.bton3);
        final Button boton4 = (Button) findViewById(R.id.bton4);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boton1.setBackgroundColor(Color.rgb(243, 230, 248)
                );
                boton2.setBackgroundColor(Color.rgb(247, 193, 234)
                );
                boton3.setBackgroundColor(Color.rgb(247, 193, 234)
                );
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boton2.setBackgroundColor(Color.rgb(243, 230, 248)
                );
                boton1.setBackgroundColor(Color.rgb(247, 193, 234)
                );
                boton3.setBackgroundColor(Color.rgb(247, 193, 234)
                );
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boton3.setBackgroundColor(Color.rgb(243, 230, 248)
                );
                boton2.setBackgroundColor(Color.rgb(247, 193, 234)
                );
                boton1.setBackgroundColor(Color.rgb(247, 193, 234)
                );
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InicioActivity.this, "Accediendo a la siguiente pantalla", Toast.LENGTH_SHORT).show();
                Intent actEncu = new Intent(InicioActivity.this,EncuestaActivity.class);
                startActivity(actEncu);
            }
        });
        // Asocio el menú contextual a la etiqueta
        final TextView etiqueta = findViewById(R.id.text3);
        registerForContextMenu(etiqueta);

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


        switch (item.getItemId()){

            case R.id.Mn1:
                Intent actInicio = new Intent(InicioActivity.this,EncuestaActivity.class);
                startActivity(actInicio);
                return true;

            case R.id.Mn2:

                return true;

            case R.id.Mn3:

                return true;

            case R.id.MnOp1:

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.692540, -4.440447?z=18"));
                startActivity(intent);
                return true;

            case R.id.MnOp2:

                Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_SUBJECT,"Problema con la aplicación");
                intent2.putExtra(Intent.EXTRA_TEXT,"No consigo acceder a mi monedero");
                intent2.putExtra(Intent.EXTRA_EMAIL, new String[]{"soporte@g.micafeteria.es"});
                startActivity(intent2);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}