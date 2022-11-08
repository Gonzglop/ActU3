package com.example.ejemplobasedatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Datos> datos;
    private Context context;

    public Adaptador(ArrayList<Datos> datos, Context context) {
        super();
        this.datos = datos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return datos.get(posicion);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mostrado = LayoutInflater.from(context);
        View elemento = mostrado.inflate(R.layout.elemento,viewGroup,false);
        final TextView texto1  = elemento.findViewById(R.id.miTexto1);
        texto1.setText(datos.get(i).getTexto1());
        final TextView texto2  = elemento.findViewById(R.id.miTexto2);
        texto2.setText(datos.get(i).getTexto2());
        final ImageView icono = elemento.findViewById(R.id.imagen);
        icono.setImageResource(datos.get(i).getIcono());
        final TextView texto  = elemento.findViewById(R.id.miTexto);
        texto1.setText(datos.get(i).getTexto1());

        return elemento;
    }
}
