package com.example.examencp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactosAdapter extends ArrayAdapter<Contacto> {

    private Context context;

    public ContactosAdapter(Context context, List<Contacto> contactos) {
        super(context, 0, contactos);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_contacto, parent, false);
        }

        Contacto contacto = getItem(position);

        ImageView imagenView = listItemView.findViewById(R.id.contacto_avatar);
        imagenView.setImageResource(contacto.getAvatar());

        TextView tituloView = listItemView.findViewById(R.id.contacto_nombre);
        tituloView.setText(contacto.getNombre());

        TextView telefonoView = listItemView.findViewById(R.id.contacto_telefono);
        telefonoView.setText(String.valueOf(contacto.getTelefono()));

        return listItemView;
    }
}
