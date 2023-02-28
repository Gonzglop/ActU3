package com.example.examencp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ContactosDataSource {

    private SQLiteDatabase database;
    private ContactosDBHelper dbHelper;


    public ContactosDataSource(Context context) {
        dbHelper = new ContactosDBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void crearContacto(String nombre, String telefono, int avatar) {
        ContentValues values = new ContentValues();
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO, telefono);
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR, avatar);
        database.insert(ContactosProvider.ContactoEntry.TABLE_NAME, null, values);
    }

    public void actualizarContacto(long id, String nombre, String telefono, int avatar) {
        ContentValues values = new ContentValues();
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO, telefono);
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR, avatar);
        String selection = ContactosProvider.ContactoEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        database.update(ContactosProvider.ContactoEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void borrarContacto(long id) {
        String selection = ContactosProvider.ContactoEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        database.delete(ContactosProvider.ContactoEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<Contacto> obtenerTodosLosContactos() {
        List<Contacto> contactos = new ArrayList<Contacto>();

        Cursor cursor = database.query(ContactosProvider.ContactoEntry.TABLE_NAME,
                ContactosProvider.allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contacto contacto = cursorToContacto(cursor);
            contactos.add(contacto);
            cursor.moveToNext();
        }
        cursor.close();
        return contactos;
    }

    private Contacto cursorToContacto(Cursor cursor) {
        Contacto contacto = new Contacto();
        contacto.setId(cursor.getInt(0));
        contacto.setNombre(cursor.getString(1));
        contacto.setTelefono(cursor.getString(2));
        contacto.setAvatar(cursor.getInt(3));
        return contacto;
    }
}

