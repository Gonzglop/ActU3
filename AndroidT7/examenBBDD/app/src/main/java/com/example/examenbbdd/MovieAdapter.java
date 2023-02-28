package com.example.examenbbdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieItem> {

    private ArrayList<MovieItem> movieList;

    public MovieAdapter(Context context, ArrayList<MovieItem> movieList) {
        super(context,R.layout.movie_item,movieList);
        this.movieList = movieList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View viewInflated = layoutInflater.inflate(R.layout.movie_item,parent,false);

        ImageView movieImage = viewInflated.findViewById(R.id.movie_image);
        movieImage.setImageResource(movieList.get(position).getImage());

        TextView movieTitle = viewInflated.findViewById(R.id.movie_title);
        movieTitle.setText(movieList.get(position).getTitle());

        return viewInflated;
    }
}
