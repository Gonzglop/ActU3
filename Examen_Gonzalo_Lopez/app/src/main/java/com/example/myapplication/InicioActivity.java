package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        final Button boton1 = (Button) findViewById(R.id.bton1);
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
    }
}