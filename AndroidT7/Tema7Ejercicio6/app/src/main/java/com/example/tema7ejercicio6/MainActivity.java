package com.example.tema7ejercicio6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etApellidos, etCodigo;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etCodigo = findViewById(R.id.etCodigo);
        etCodigo.setVisibility(View.GONE);
        findViewById(R.id.btnActualizar).setVisibility(View.GONE);
        findViewById(R.id.btnBorrar).setVisibility(View.GONE);

        // Crear o abrir la base de datos
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertar:
                insertar();
                break;
            case R.id.btnConsultar:
                consultar();
                break;
            case R.id.btnActBorr:
                etCodigo.setVisibility(View.VISIBLE);
                findViewById(R.id.btnActualizar).setVisibility(View.VISIBLE);
                findViewById(R.id.btnBorrar).setVisibility(View.VISIBLE);
                break;
            case R.id.btnActualizar:
                actualizar();
                break;
            case R.id.btnBorrar:
                borrar();
                break;
        }
    }

    private void insertar() {
        ContentValues cv = new ContentValues();
        cv.put("nombre", etNombre.getText().toString());
        cv.put("apellidos", etApellidos.getText().toString());
        long res = db.insert("datos", null, cv);
        if (res != -1) {
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "Error al insertar el registro", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    private void consultar() {
        Cursor c = db.query("datos", null, null, null, null, null, null);
        StringBuilder sb = new StringBuilder();
        if (c.moveToFirst()) {
            do {
                sb.append("Código: ").append(c.getInt(c.getColumnIndex("codigo"))).append("\n")
                        .append("Nombre: ").append(c.getString(c.getColumnIndex("nombre"))).append("\n")
                        .append("Apellidos: ").append(c.getString(c.getColumnIndex("apellidos"))).append("\n\n");
            } while (c.moveToNext());
        }
        etNombre.setText(sb.toString());
    }

    private void actualizar() {
        ContentValues cv = new ContentValues();
        cv.put("nombre", etNombre.getText().toString());
        cv.put("apellidos", etApellidos.getText().toString());
        int res = db.update("datos", cv, "codigo = ?", new String[]{etCodigo.getText().toString()});
        if (res > 0) {
            Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
            etCodigo.setVisibility(View.GONE);
            findViewById(R.id.btnActualizar).setVisibility(View.GONE);
            findViewById(R.id.btnBorrar).setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Error al actualizar el registro", Toast.LENGTH_SHORT).show();
        }
    }

    private void borrar() {
        int res = db.delete("datos", "codigo = ?", new String[]{etCodigo.getText().toString()});
        if (res > 0) {
            Toast.makeText(this, "Registro borrado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
            etCodigo.setVisibility(View.GONE);
            findViewById(R.id.btnActualizar).setVisibility(View.GONE);
            findViewById(R.id.btnBorrar).setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Error al borrar el registro", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etApellidos.setText("");
        etCodigo.setText("");
    }

    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "appdb", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE datos (_id INTEGER PRIMARY KEY, nombre TEXT, apellidos TEXT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS datos");
            onCreate(db);
        }
    }
}