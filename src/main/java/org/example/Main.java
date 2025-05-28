package org.example;

import org.example.config.DBManager;
import org.example.controller.BookController;
import org.example.repository.BookRepository;
import org.example.view.BookView;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookController bookController = new BookController(bookRepository);
        BookView bookView = new BookView(bookController);

        bookView.createBook( "1984",
                             "A dystopian novel by George Orwell",
                             "1234567890",
                             "George Orwell",
                             "Dystopian");

        bookView.displayBooks();
    }
}