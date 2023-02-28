package com.example.examenbbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MovieSQL extends SQLiteOpenHelper {

    private static final String KEY_ID = "ID";
    public static final String KEY_TITLE = "TITLE";
    public static final String KEY_IMAGE = "IMAGE";
    public static final String KEY_LIST = "LIST";

    String CREATE_MOVIES_TABLE = "CREATE TABLE TABLE_MOVIES" + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TITLE + " TEXT,"
            + KEY_IMAGE + " INTEGER,"
            + KEY_LIST + " INTEGER" + ")";

    public MovieSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL(CREATE_MOVIES_TABLE);
    }
}
