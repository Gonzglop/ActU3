package com.example.tema7ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private TextView textView1;
    private TextView textView2;
    private Button saveButton;
    private Button retrieveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los objetos en el layout
        editText1 = findViewById(R.id.edit_text1);
        editText2 = findViewById(R.id.edit_text2);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        saveButton = findViewById(R.id.save_button);
        retrieveButton = findViewById(R.id.retrieve_button);

        // Inicializar SharedPreferences
        sharedPreferences = getPreferences(MODE_PRIVATE);

        // Establecer eventos onClick para los botones
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Guardar el contenido de los EditText en las preferencias compartidas
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("edit_text1", editText1.getText().toString());
                editor.putString("edit_text2", editText2.getText().toString());
                editor.apply();
                Toast.makeText(MainActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
            }
        });
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperar el contenido almacenado en las preferencias compartidas
                textView1.setText(sharedPreferences.getString("edit_text1", ""));
                textView2.setText(sharedPreferences.getString("edit_text2", ""));
                Toast.makeText(MainActivity.this, "Datos recuperados", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
