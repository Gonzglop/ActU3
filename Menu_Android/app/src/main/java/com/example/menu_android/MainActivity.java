package com.example.menu_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//Creamos menú de opciones desde XML
/*
    private static final int MnOp1 = 1;
    private static final int MnOp2 = 2;
    private static final int MnOp2_1 = 2;
    private static final int MnOp2_2 = 2;
    //private static final int MnOp3 = 3;
    private static final int MnOp3 = 5;



 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,MnOp1,Menu.NONE,"Opción A desde Java");

        Submenu smnu = menu.addSubMenu(Menu.NONE,MnOp2,Menu.NONE,"Opción B desde java");
        smnu.add(Menu.NONE,MnOp2,Menu.NONE,"Opción B desde Java");
        //menu.add(Menu.NONE,MnOp2,Menu.NONE,"Opción B desde Java");
        menu.add(Menu.NONE,MnOp3,Menu.NONE,"Opción C desde Java");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MnOp1:
                Toast.makeText(this, "Opción1", Toast.LENGTH_SHORT).show();
                return true;

            case MnOp2:
                Toast.makeText(this, "Opción2", Toast.LENGTH_SHORT).show();
                return true;

            case MnOp3:
                Toast.makeText(this, "Opción3", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

 */

//Creamos menú de opciones desde XML




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.MnOp1:
                Toast.makeText(this, "Opción1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.MnOp2:
                Toast.makeText(this, "Opción2", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.MnOp2_1:
                Toast.makeText(this, "Opción2.1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.MnOp2_2:
                Toast.makeText(this, "Opción2.2", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.MnOp3:
                Toast.makeText(this, "Opción3", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}