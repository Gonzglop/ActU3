package com.example.tema7ejercicio5;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button saveButton, retrieveButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);
        retrieveButton = findViewById(R.id.retrieveButton);
        textView = findViewById(R.id.textView);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveData();
            }
        });
    }

    private void saveData() {
        try {
            // Ruta para directorios predefinidos
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
            OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
            fout.write(editText.getText().toString());
            fout.close();
        }
        catch (Exception ex){
            Log.e("Ficheros","Error al escribir en la tarjeta SD");
        }
    }

    private void retrieveData() {
        try {
            File ruta_sd = getExternalFilesDir(null);
            File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            textView.setText(fin.readLine());
            fin.close();
        }
        catch (Exception ex){
            Log.e("Ficheros","Error al leer en la tarjeta SD");
        }
    }
}
