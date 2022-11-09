package com.example.android_t5_ej3;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class SetImageAdapter extends BaseAdapter {
    public Integer[] thumbImages = {
            R.drawable.consola,
            R.drawable.ordenador_fijo,
            R.drawable.ordenador_portatil,
            R.drawable.smartphone,
            R.drawable.tablet,
            R.drawable.tv
    };
    private Context Cont;

    public SetImageAdapter(Context c) {
        Cont = c;
    }

    public int getCount() {
        return thumbImages.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgview = new ImageView(Cont);
        imgview.setLayoutParams(new GridView.LayoutParams(370, 250));
        imgview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imgview.setPadding(10,10,10, 10);
        imgview.setImageResource(thumbImages[position]);
        return imgview;
    }
}