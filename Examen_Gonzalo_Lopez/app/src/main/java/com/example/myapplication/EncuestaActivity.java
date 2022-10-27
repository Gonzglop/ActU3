package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class EncuestaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta);

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
}