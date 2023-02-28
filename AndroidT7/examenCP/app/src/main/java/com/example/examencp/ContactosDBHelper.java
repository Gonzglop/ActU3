package com.example.examencp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ContactosDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactos_db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContactosProvider.ContactoEntry.TABLE_NAME + " (" +
                    ContactosProvider.ContactoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ContactosProvider.ContactoEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    ContactosProvider.ContactoEntry.COLUMN_NAME_TELEFONO + " TEXT," +
                    ContactosProvider.ContactoEntry.COLUMN_NAME_AVATAR + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContactosProvider.ContactoEntry.TABLE_NAME;

    public ContactosDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
