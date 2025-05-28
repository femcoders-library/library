package org.example.controller;

import org.example.model.Book;
import org.example.repository.BookRepository;

import java.util.List;

public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = new BookRepository();
    }
    public void createBook(String title, String synopsis, String isbn, String author, String genre) {
        bookRepository.addBook(title, synopsis, isbn, author, genre);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }
}
