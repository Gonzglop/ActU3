package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actividad1();

        //actividad2();

        //actividad3();
    }

    private void actividad3() {
        final TextView textSpinnner = (TextView) findViewById(R.id.spinnertext);
        final Spinner spinner = (Spinner) findViewById(R.id.miSpinner);

        String[] valores = {"1 día", "2 días", "3 días", "4 días", "5 días", "6 días", "7 días"};

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adaptador, View view, int posicion, long id) {
                adaptador.getItemAtPosition(posicion);
                textSpinnner.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(
                    AdapterView<?> adapterView) {
            }
        });
    }

    private void actividad2() {
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
                Toast.makeText(MainActivity.this, "Accediendo a la siguiente pantalla", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void actividad1() {

        final EditText input1 = (EditText) findViewById(R.id.lbl1);
        final EditText input2 = (EditText) findViewById(R.id.lbl2);

        final Button boton = (Button) findViewById(R.id.btn1);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                input1.setAlpha(1);
                input2.setAlpha(1);
                boton.setAlpha(1);
            }
        }, 3000);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto1 = String.valueOf(input1.getText());
                String texto2 = String.valueOf(input2.getText());

                if (texto1.equals("")|texto2.equals("")) {
                    Toast.makeText(MainActivity.this, "Introduce usuario y clave", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Hola " + input1.getText() + ", accediendo a la app ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}