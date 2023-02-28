package com.example.examenbbdd;

public class MovieItem {
    private int id;
    private int image;
    private String title;
    private int state;

    public MovieItem(int id, int image, String title, int state) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public int getState() {
        return state;
    }
}

