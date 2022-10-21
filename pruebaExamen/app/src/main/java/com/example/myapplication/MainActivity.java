package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView etiqueta = (TextView) findViewById(R.id.miEtiq);
        final CheckBox check = (CheckBox) findViewById(R.id.miCheck);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked){
                    etiqueta.setText("Seleccionado");
                }else{
                    etiqueta.setText("No seleccionado");
                }
            }
        });

        //Asocio un listener con los radioButton

        final RadioGroup grupo= (RadioGroup) findViewById(R.id.grupo);
        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedid) {
                final RadioButton boton = (RadioButton) findViewById(checkedid);
                etiqueta.setText(boton.getText());

            }
        });

        //Asocio el listener con el Switch

        final Switch deslizador = (android.widget.Switch) findViewById(R.id.miSwitch);
        deslizador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //etiqueta.setText("Switch pulsado");
                    compoundButton.setText("Pulsado");
                }else{
                    //etiqueta.setText("Switch no pulsad");
                    compoundButton.setText("No pulsado");
                }
            }
        });

        RatingBar miControl = (RatingBar) findViewById(R.id.miRating);
        miControl.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                etiqueta.setText(String.valueOf((int) miControl.getRating()));
                Toast.makeText(getApplicationContext(),String.valueOf((int) miControl.getRating()),Toast.LENGTH_LONG).show();
            }
        });

        //creo el manejador para el Seekbar
        final SeekBar miSeek = findViewById(R.id.miSeekBar);
        final TextView mitexto = findViewById(R.id.miEtiq2);

        miSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                etiqueta.setText("Texto cambiando en Seekbar");
                mitexto.setAlpha((i/6f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                etiqueta.setText("Inicio del cambio");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                etiqueta.setText("Final del cambio");
            }
        });




    }
}