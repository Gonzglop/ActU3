package com.example.android_t5_ej8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final TextView etiqueta = (TextView) findViewById(R.id.lbl1);

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


            case R.id.MnM1:
                etiqueta.setText("PULSADO EL MES DE ENERO");
                return true;

            case R.id.MnM2:
                etiqueta.setText("PULSADO EL MES DE FEBRERO");
                return true;

            case R.id.MnM3:
                etiqueta.setText("PULSADO EL MES DE MARZO");
                return true;

            case R.id.MnM4:
                etiqueta.setText("PULSADO EL MES DE ABRIL");
                return true;

            case R.id.MnM5:
                etiqueta.setText("PULSADO EL MES DE MAYO");
                return true;

            case R.id.MnM6:
                etiqueta.setText("PULSADO EL MES DE JUNIO");
                return true;

            case R.id.MnM7:
                etiqueta.setText("PULSADO EL MES DE JULIO");
                return true;

            case R.id.MnM8:
                etiqueta.setText("PULSADO EL MES DE AGOSTO");
                return true;

            case R.id.MnM9:
                etiqueta.setText("PULSADO EL MES DE SEPTIEMBRE");
                return true;

            case R.id.MnM10:
                etiqueta.setText("PULSADO EL MES DE OCTUBRE");
                return true;

            case R.id.MnM11:
                etiqueta.setText("PULSADO EL MES DE NOVIEMBRE");
                return true;

            case R.id.MnM12:
                etiqueta.setText("PULSADO EL MES DE DICIEMBRE");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}