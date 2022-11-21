package com.example.android_t5_ej7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        TextView etiqueta = findViewById(R.id.lbl1);

        switch (item.getItemId()){
            case R.id.MnOp1:
                etiqueta.setText("PULSADO LUNES");
                return true;

            case R.id.MnOp2:
                etiqueta.setText("PULSADO MARTES");
                return true;

            case R.id.MnOp3:
                etiqueta.setText("PULSADO MIÉRCOLES");
                return true;

            case R.id.MnOp4:
                etiqueta.setText("PULSADO JUEVES");
                return true;

            case R.id.MnOp5:
                etiqueta.setText("PULSADO VIERNES");
                return true;

            case R.id.MnOp6:
                etiqueta.setText("PULSADO SÁBADO");
                return true;

            case R.id.MnOp7:
                etiqueta.setText("PULSADO DOMINGO");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}