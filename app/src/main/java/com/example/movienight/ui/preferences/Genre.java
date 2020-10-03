package com.example.movienight.ui.preferences;

public class Genre {
    private int id;
    private String name;
    private String[] content = new String[2];

    public Genre(int id, String name){
        this.id = id;
        this.name = name;
        this.content[0] = name;
        this.content[1] = id + "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return name;
    }
}
