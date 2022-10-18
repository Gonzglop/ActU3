package com.example.proyecto3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editNombre=findViewById(R.id.txtNombre);
        EditText editFechaNac=findViewById(R.id.txtFechaNac);
        CheckBox cbRecordatorio=findViewById(R.id.check1);
        Button btnAceptar=findViewById(R.id.btnAceptar);
        TextView txtMensaje=findViewById(R.id.txtMensaje);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = String.valueOf(editNombre.getText());
                String fecha = String.valueOf(editFechaNac.getText());

                if (nombre.isEmpty() && fecha.isEmpty()){
                    txtMensaje.setTextColor(Color.RED);
                    txtMensaje.setText("No has introducido ningún dato...");

                }else if(nombre.isEmpty()&& !fecha.isEmpty()){
                    txtMensaje.setTextColor(Color.RED);
                    txtMensaje.setText("No has escrito el nombre");

                }else if(!nombre.isEmpty() && fecha.isEmpty()){
                    txtMensaje.setTextColor(Color.RED);
                    txtMensaje.setText("No has escrito la fecha de nacimiento");

                }else if (!fecha.isEmpty()&& !nombre.isEmpty() && !cbRecordatorio.isChecked()){
                    txtMensaje.setTextColor(Color.GRAY);
                    txtMensaje.setText(nombre + ", pulsa la casilla para crear un recordatorio.");

                }else if(!fecha.isEmpty()&& !nombre.isEmpty() && cbRecordatorio.isChecked()){
                    txtMensaje.setTextColor(Color.WHITE);

                    txtMensaje.setText("¡Hola " + nombre + "! \n\nTu fecha de nacimiento es: " + fecha + "\n\nSe ha creado un recordatorio.");
                }
            }
        });
    }
}