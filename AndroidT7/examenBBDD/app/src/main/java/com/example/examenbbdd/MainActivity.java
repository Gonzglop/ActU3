package com.example.examenbbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MovieSQL movieSQL;
    SQLiteDatabase db;
    ArrayList<MovieItem> movieArrayList;
    ArrayList<MovieItem> favMovieArrrayList = new ArrayList<>();
    MovieAdapter movieAdapter;
    MovieAdapter favMovieAdapter;
    ListView movieList;
    ListView favMovieList;
    LinearLayout addFavoritesLayout;
    LinearLayout removeFavoritesLayout;
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = findViewById(R.id.list_movies);
        favMovieList =findViewById(R.id.list_fav);

        addFavoritesLayout =findViewById(R.id.favorites_layout);
        removeFavoritesLayout =findViewById(R.id.remove_favorites_layout);

        movieSQL = new MovieSQL(this,"MOVIES_DB",null,1);
        db = movieSQL.getWritableDatabase();
        insertMovies();
        showMovieList();


        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                addFavoritesLayout.setVisibility(View.VISIBLE);
                removeFavoritesLayout.setVisibility(View.GONE);
                selectedItem = ((MovieItem)adapterView.getItemAtPosition(position)).getId();
            }
        });
        favMovieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                removeFavoritesLayout.setVisibility(View.VISIBLE);
                addFavoritesLayout.setVisibility(View.GONE);
                selectedItem = ((MovieItem)adapterView.getItemAtPosition(position)).getId();
            }
        });

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_favorite_button:
                addList();
                break;
            case R.id.cancel_button1:
                addFavoritesLayout.setVisibility(View.GONE);
                break;

            case R.id.remove_favorite_button:
                removeList();
                break;
            case R.id.cancel_button2:
                removeFavoritesLayout.setVisibility(View.GONE);
                break;
        }
    }

    public void insertMovies(){
        String sqlCreate = "CREATE TABLE TABLE_MOVIES " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE TEXT, " +
                "IMAGE INT," +
                "LIST INT)";

        try {
            //db.execSQL("DROP TABLE IF EXISTS TABLE_MOVIES");
            db.execSQL(sqlCreate);
            db.execSQL("INSERT INTO TABLE_MOVIES(TITLE, IMAGE, LIST) VALUES " +
                    "('Alquimia',"+R.drawable.alquimia+",0)," +
                    "('Breaking Bad',"+R.drawable.breaking+",0)," +
                    "('Broadchurch',"+R.drawable.broadchurch+",0)," +
                    "('Erased',"+R.drawable.erased+",0)," +
                    "('Hill',"+R.drawable.hill+",0)," +
                    "('Howl',"+R.drawable.howl+",0)," +
                    "('Lucifer',"+R.drawable.lucifer+",0)," +
                    "('Stranger Things',"+R.drawable.stranger+",0)," +
                    "('Sobrenatural',"+R.drawable.supernatural+",0)," +
                    "('Titanes',"+R.drawable.titanes+",0)" +
                    "");

        }catch (Exception e){
            System.out.println("Error al insertar los datos.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu ){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this,ConfigActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public  void showMovieList(){
        movieArrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT ID, TITLE, IMAGE, LIST " +
                "FROM TABLE_MOVIES", null);
        MovieItem movieItem;
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0) ;
                String title = cursor.getString(1);
                int image =cursor.getInt(2);
                int state=cursor.getInt(3);

                movieItem= new MovieItem(id,image,title,state);
                movieArrayList.add(movieItem);
                if (state==1){
                    favMovieArrrayList.add(movieItem);
                }

            } while(cursor.moveToNext()); }

        movieAdapter = new MovieAdapter(this, movieArrayList);
        movieList.setAdapter(movieAdapter);

        favMovieAdapter = new MovieAdapter(this, favMovieArrrayList);
        favMovieList.setAdapter(favMovieAdapter);
    }

    public  void addList(){
        Cursor cursor = db.rawQuery("SELECT ID, TITLE, IMAGE, LIST FROM TABLE_MOVIES WHERE ID=" +
                selectedItem,null);
        MovieItem movieItem;
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0) ;
                String title = cursor.getString(1);
                int image =cursor.getInt(2);
                int state=cursor.getInt(3);

                if (state!=1){
                    movieItem= new MovieItem(id,image,title,state);
                    favMovieArrrayList.add(movieItem);
                    db.execSQL("UPDATE TABLE_MOVIES SET LIST=" + 1 + " WHERE ID="+ id);
                }else {
                    Toast.makeText(this, "Esta película ya se encuentra en tus favoritos",
                            Toast.LENGTH_SHORT).show();
                }

            } while(cursor.moveToNext()); }
        favMovieAdapter = new MovieAdapter(this, favMovieArrrayList);
        favMovieList.setAdapter(favMovieAdapter);
    }

    public  void removeList(){
        Cursor cursor = db.rawQuery("SELECT ID, TITLE, IMAGE, LIST FROM TABLE_MOVIES WHERE ID=" +
                selectedItem,null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0) ;
                int state=cursor.getInt(3);

                if (state == 1){
                    for (int i = 0; i< favMovieArrrayList.size(); i++) {
                        if (favMovieArrrayList.get(i).getId() == id){
                            favMovieArrrayList.remove(favMovieArrrayList.get(i));
                        }
                    }
                    db.execSQL("UPDATE TABLE_MOVIES SET LIST=" + 0 + " WHERE ID=" + id);
                }
            } while(cursor.moveToNext());
        }
        favMovieAdapter = new MovieAdapter(this, favMovieArrrayList);
        favMovieList.setAdapter(favMovieAdapter);
    }
}