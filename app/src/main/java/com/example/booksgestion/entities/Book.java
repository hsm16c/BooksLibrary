package com.example.booksgestion.entities;

public class Book {
    private int idbook ;
    private String book_name;
    private String book_author;
    private int year_publication;

    public Book(int idbook, String book_name, String book_author, int year_publication) {
        this.idbook = idbook;
        this.book_name = book_name;
        this.book_author = book_author;
        this.year_publication = year_publication;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(int year_publication) {
        this.year_publication = year_publication;
    }
}

