package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntentExp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_exp);

        final Button boton = (Button) findViewById(R.id.btn1);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actInicio = new Intent(IntentExp.this,MainActivity.class);
                startActivity(actInicio);
            }
        });
    }
}