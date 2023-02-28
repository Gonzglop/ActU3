package com.example.examencp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactosProvider extends ContentProvider {

    private static final String TAG = ContactosProvider.class.getSimpleName();

    private static final String AUTORIDAD = "com.example.examencp";
    private static final String URI_BASE = "content://" + AUTORIDAD;
    private static final String PATH_CONTACTOS = ContactoEntry.TABLE_NAME;

    public static final Uri URI_CONTENIDO = Uri.parse(URI_BASE + "/" + PATH_CONTACTOS);

    private static final int TODO = 1;
    private static final int UNO = 2;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTORIDAD, PATH_CONTACTOS, TODO);
        URI_MATCHER.addURI(AUTORIDAD, PATH_CONTACTOS + "/#", UNO);
    }

    public static class ContactoEntry implements BaseColumns {
        public static final String TABLE_NAME = "contactos";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_AVATAR = "avatar";

        public static Uri crearUriContacto(long id) {
            return URI_CONTENIDO.buildUpon().appendPath(String.valueOf(id)).build();
        }
    }

    public static String[] allColumns = {
            ContactosProvider.ContactoEntry._ID,
            ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE,
            ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO,
            ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR
    };


    private SQLiteDatabase database;
    private ContactosDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new ContactosDBHelper(context);
        database = dbHelper.getWritableDatabase();
        return database != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (URI_MATCHER.match(uri)) {
            case TODO:
                cursor = database.query(ContactosProvider.ContactoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case UNO:
                long id = ContentUris.parseId(uri);
                cursor = database.query(ContactosProvider.ContactoEntry.TABLE_NAME, projection, ContactosProvider.ContactoEntry._ID + " = " + id, null, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        int match = URI_MATCHER.match(uri);

        switch (match){
            case TODO:
                return "vnd.android.cursor.dir/vnd.examencp.contacto";
            case UNO:
                return "vnd.android.cursor.item/vnd.examencp.contacto";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(ContactosProvider.ContactoEntry.TABLE_NAME, null, values);
        if (id > 0) {
            Uri uriInsertada = ContentUris.withAppendedId(URI_CONTENIDO, id);
            getContext().getContentResolver().notifyChange(uriInsertada, null);
            return uriInsertada;
        } else {
            Log.e(TAG, "Error al insertar fila en: " + uri);
            return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int filasActualizadas = 0;
        switch (URI_MATCHER.match(uri)) {
            case TODO:
                filasActualizadas = database.update(ContactosProvider.ContactoEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case UNO:
                long id = ContentUris.parseId(uri);
                filasActualizadas = database.update(ContactosProvider.ContactoEntry.TABLE_NAME, values, ContactosProvider.ContactoEntry._ID + " = " + id, null);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return filasActualizadas;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int filasEliminadas = 0;
        switch (URI_MATCHER.match(uri)) {
            case TODO:
                filasEliminadas = database.delete(ContactosProvider.ContactoEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case UNO:
                long id = ContentUris.parseId(uri);
                filasEliminadas = database.delete(ContactosProvider.ContactoEntry.TABLE_NAME, ContactosProvider.ContactoEntry._ID + " = " + id, null);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return filasEliminadas;
    }
}