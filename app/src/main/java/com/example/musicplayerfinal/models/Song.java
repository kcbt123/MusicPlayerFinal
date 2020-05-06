package com.example.musicplayerfinal.models;

public class Song {

    private String name;
    private String author;
    private int file;

    public Song(String name, String author, int file) {
        this.name = name;
        this.author = author;
        this.file = file;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getFile() {
        return this.file;
    }

}
