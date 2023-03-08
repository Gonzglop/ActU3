package com.example.examencp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtTelefono;
    private ImageButton btnAddContact;
    private ViewGroup layoutAddContact;
    private Button btnAdd;
    private Button btnCancelar;
    private Button btnModificar;
    private ListView listaContactos;
    private List<Contacto> contactos;
    private Spinner spinnerImagen;
    private Boolean mostrar = true;
    private int avatarId;
    private Contacto contactoSeleccionado = null;
    List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.editTextNombre);
        txtTelefono = findViewById(R.id.editTextTelefono);
        btnAddContact = findViewById(R.id.imageButtonAdd);
        layoutAddContact = findViewById(R.id.layoutDatosContacto);
        btnAdd = findViewById(R.id.btn_add_contacto);
        btnCancelar = findViewById(R.id.btn_cancelar);
        btnModificar = findViewById(R.id.btn_modificar);
        listaContactos = findViewById(R.id.lista_contactos);
        contactos = new ArrayList<>();
        spinnerImagen = findViewById(R.id.spinner_imagen);

        images = Arrays.asList(
                R.drawable.batman,
                R.drawable.capi,
                R.drawable.deadpool,
                R.drawable.furia,
                R.drawable.hulk,
                R.drawable.ironman,
                R.drawable.lobezno,
                R.drawable.spiderman,
                R.drawable.thor,
                R.drawable.wonderwoman
        );

        ImageAdapter adapter = new ImageAdapter(this, images);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImagen.setAdapter(adapter);

        registerForContextMenu(listaContactos);
        consulta();

        spinnerImagen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                avatarId = (int) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLayout(null);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLayout(null);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(ContactosProvider.allColumns[1], txtNombre.getText().toString());
                values.put(ContactosProvider.allColumns[2], txtTelefono.getText().toString());
                values.put(ContactosProvider.allColumns[3], avatarId);
                ContentResolver cr = getContentResolver();

                cr.insert(ContactosProvider.URI_CONTENIDO, values);

                consulta();
                mostrarLayout(null);
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = contactoSeleccionado.getId();

                ContentValues values = new ContentValues();
                values.put(ContactosProvider.allColumns[1], txtNombre.getText().toString());
                values.put(ContactosProvider.allColumns[2], txtTelefono.getText().toString());
                values.put(ContactosProvider.allColumns[3], avatarId);
                ContentResolver cr = getContentResolver();

                cr.update(ContactosProvider.URI_CONTENIDO, values, ContactosProvider.allColumns[0] + "=?", new String[]{String.valueOf(id)});

                consulta();
                mostrarLayout(null);
            }
        });
    }

    private void consulta() {

        Uri clientesUri = ContactosProvider.URI_CONTENIDO;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(clientesUri, ContactosProvider.allColumns,null,null, null);

        if (cur != null) {
            if (cur.moveToFirst()) {
                int id;
                String nombre;
                String telefono;
                int avatar;

                int colId = cur.getColumnIndex(ContactosProvider.ContactoEntry._ID);
                int colNombre = cur.getColumnIndex(ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE);
                int colTelefono = cur.getColumnIndex(ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO);
                int colAvatar = cur.getColumnIndex(ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR);

                contactos.clear();

                do {
                    id = cur.getInt(colId);
                    nombre = cur.getString(colNombre);
                    telefono = cur.getString(colTelefono);
                    avatar = cur.getInt(colAvatar);

                    Contacto contacto = new Contacto();
                    contacto.setId(id);
                    contacto.setNombre(nombre);
                    contacto.setTelefono(telefono);
                    contacto.setAvatar(avatar);

                    contactos.add(contacto);

                } while (cur.moveToNext());

                if (contactos != null) {
                    ContactosAdapter adapter = new ContactosAdapter(this, contactos);
                    listaContactos.setAdapter(adapter);
                }
            }
        }
    }

    private void mostrarLayout(Contacto contacto) {

        if (mostrar) {
            if (contacto != null) {
                txtNombre.setText(contacto.getNombre());
                txtTelefono.setText(contacto.getTelefono());
                spinnerImagen.setSelection(images.indexOf(contacto.getAvatar()));
            }

            layoutAddContact.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            btnCancelar.setVisibility(View.VISIBLE);

            mostrar = false;

        } else {
            layoutAddContact.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);
            btnCancelar.setVisibility(View.GONE);
            btnModificar.setVisibility(View.GONE);

            txtNombre.setText("");
            txtTelefono.setText("");
            spinnerImagen.setSelection(0);

            mostrar = true;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        contactoSeleccionado = contactos.get(info.position);
        int id = contactoSeleccionado.getId();

        switch (item.toString()) {
            case "Borrar":
                borrarContacto(id);
                return true;
            case "Actualizar":
                mostrarModificarContacto(contactoSeleccionado);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void borrarContacto(int id) {
        ContentResolver cr = getContentResolver();
        cr.delete(ContactosProvider.URI_CONTENIDO, ContactosProvider.ContactoEntry._ID + "=" + id, null);
        consulta();
    }

    private void mostrarModificarContacto(Contacto contacto) {
        mostrar = true;
        mostrarLayout(contacto);//pasar contacto seleccionado
        btnAdd.setVisibility(View.GONE);
        btnModificar.setVisibility(View.VISIBLE);
        mostrar = false;
    }
}