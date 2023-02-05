package com.gonz.pizzeria;

import java.util.List;

public class Pizza {
    private int size;
    private String type;
    private List<String> extras;

    public Pizza(int size, String type, List<String> extras) {
        this.size = size;
        this.type = type;
        this.extras = extras;
    }

    public Pizza() {

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }
}