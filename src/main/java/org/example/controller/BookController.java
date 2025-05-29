package org.example.controller;

import org.example.model.Book;
import org.example.repository.BookRepository;

import java.util.List;

public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void createBook(Book book){
        bookRepository.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }
}
