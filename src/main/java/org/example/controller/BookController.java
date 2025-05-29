package org.example.controller;

import org.example.model.Book;
import org.example.repository.BookRepository;

import java.util.List;
import java.util.Map;

public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.addBook(book);
    }

    public void updateBook(String isbn, Book book) {
        bookRepository.updateBook(isbn, book);
    }

    public void updateBookByField(String isbn, Map<String, String> fieldsToUpdate) {
        bookRepository.updateBookByField(isbn, fieldsToUpdate);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public boolean existByISBN(String isbn) {
        return bookRepository.existByISBN(isbn);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
