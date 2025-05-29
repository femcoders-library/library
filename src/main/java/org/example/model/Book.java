package org.example.model;

public class Book {
    private int id;
    private String title;
    private String synopsis;
    private String isbn;
    private String author;
    private String genre;

    public Book(String title, String synopsis, String isbn, String author, String genre) {
        this.title = title;
        this.synopsis = synopsis;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

