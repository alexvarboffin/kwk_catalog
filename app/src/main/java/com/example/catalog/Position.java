package com.example.catalog;

public class Position {
    String name;
    int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Position(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
