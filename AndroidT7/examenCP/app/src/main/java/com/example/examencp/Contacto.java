package com.example.examencp;

import android.content.ContentValues;

public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private int avatar;

    public Contacto(int id, String nombre, String telefono, int avatar) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.avatar = avatar;
    }

    public Contacto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE, nombre);
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO, telefono);
        values.put(ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR, avatar);
        return values;
    }
}
