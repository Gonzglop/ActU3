package com.example.examencp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<Integer> {
    private Context mContext;
    private List<Integer> mImages;

    public ImageAdapter(Context context, List<Integer> images) {
        super(context, 0, images);
        mContext = context;
        mImages = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;
        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        }
        imageView.setImageResource(mImages.get(position));
        return imageView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;
        if (imageView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        }
        imageView.setImageResource(mImages.get(position));
        return imageView;
    }
}
