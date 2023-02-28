package com.example.examencp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtTelefono ;
    private ImageButton btnAddContact;
    private ViewGroup layoutAddContact;
    private Button btnAdd;
    private Button btnCancelar;
    private Button btnModificar;
    private ListView listaContactos;
    private List<Contacto> contactos;
    private Spinner spinnerImagen;
    private Boolean mostrar = true;
    private int avatarId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ContactosDBHelper contactosDBHelper = new ContactosDBHelper(this);
        //SQLiteDatabase db = contactosDBHelper.getWritableDatabase();

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

        List<Integer> images = Arrays.asList(
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
                // Hacer algo con la referencia de la imagen seleccionada en drawable (avatarId)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se seleccionó nada
            }
        });



        // Asignar escuchador de eventos a la imagen addContact
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mostrar la zona de añadir/modificar contactos y ocultar los botones de modificar

                mostrarLayout( null);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mostrar la zona de añadir/modificar contactos y ocultar los botones de modificar

                mostrarLayout( null);
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

                cr.insert(ContactosProvider.URI_CONTENIDO,values);

                // Mostramos la lista actualizada de contactos
                consulta();
                // Ocultamos la zona de añadir/modificar contactos
                mostrarLayout(null);
            }
        });
    }

    private void consulta() {

        Uri clientesUri = ContactosProvider.URI_CONTENIDO;

        ContentResolver cr = getContentResolver();

        // Hacemos la consulta
        Cursor cur = cr.query(clientesUri,
                ContactosProvider.allColumns, //Columnas a devolver)
                null,       // Condición de la query
                null,       // Argumentos variables de la query
                null);      // Orden de los resultados

        if (cur!=null){
            if (cur.moveToFirst()){
                String nombre;
                String telefono;
                int avatar;

                int colNombre = cur.getColumnIndex(ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE);
                int colTelefono = cur.getColumnIndex(ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO);
                int colAvatar = cur.getColumnIndex(ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR);

                contactos.clear();

                do{
                    nombre = cur.getString(colNombre);
                    telefono = cur.getString(colTelefono);
                    avatar = cur.getInt(colAvatar);

                    Contacto contacto = new Contacto();
                    contacto.setNombre(nombre);
                    contacto.setTelefono(telefono);
                    contacto.setAvatar(avatar);

                    contactos.add(contacto);

                }while (cur.moveToNext());
                if (contactos!=null) {

                    ContactosAdapter adapter = new ContactosAdapter(this, contactos );
                    // Asignar el adaptador al ListView
                    listaContactos.setAdapter(adapter);
                }
            }

        }
    }

    private void mostrarLayout(Contacto contacto) {

        if (mostrar) {
            // Si estamos en modo de modificación, rellenamos los campos
            if (contacto != null) {
                txtNombre.setText(contacto.getNombre());
                txtTelefono.setText(contacto.getTelefono());
                spinnerImagen.setSelection(contacto.getAvatar());
            }

            // Mostramos la zona de añadir/modificar contactos y los botones de añadir y cancelar
            layoutAddContact.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            btnCancelar.setVisibility(View.VISIBLE);

            mostrar= false;
        } else {
            // Ocultamos la zona de añadir/modificar contactos y los botones de añadir, modificar y cancelar
            layoutAddContact.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);
            btnCancelar.setVisibility(View.GONE);

            // Limpiamos los campos de la zona de añadir/modificar contactos
            txtNombre.setText("");
            txtTelefono.setText("");
            spinnerImagen.setSelection(0);

            mostrar= true;
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
        int position = info.position;
        ContactosAdapter adapter = new ContactosAdapter(this, contactos);
        Cursor cursor = (Cursor) adapter.getItem(position);
        @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(ContactosProvider.ContactoEntry._ID));
        switch (item.getItemId()) {
            case R.id.menu_borrar:
                borrarContacto(id);
                return true;
            case R.id.menu_modificar:
                mostrarModificarContacto(id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void borrarContacto(long id) {
        ContentResolver cr = getContentResolver();
        cr.delete(ContactosProvider.URI_CONTENIDO, ContactosProvider.ContactoEntry._ID + "=" + id,null);

        //getContentResolver().delete(ContactosProvider.ContactoEntry.crearUriContacto(id), null, null);
        consulta();
    }

    private void mostrarModificarContacto(long id) {
        mostrar = true;
        mostrarLayout(null);
        btnAdd.setVisibility(View.GONE);
        btnModificar.setVisibility(View.VISIBLE);
        mostrar =false;
    }

}