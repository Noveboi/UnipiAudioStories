package com.example.unipiaudiostories.domain;

public class Story {
    String title;
    String content;
    String author;
    int year;
    int image;

    public Story(String title, String content, String author, int year, int image) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.year = year;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
