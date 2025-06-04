package org.example;

import org.example.controller.BookController;
import org.example.repository.BookRepository;
import org.example.view.BookView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookController bookController = new BookController(bookRepository);
        Scanner scanner = new Scanner(System.in);
        BookView bookView = new BookView(bookController, scanner);
        bookView.showMenu();

        scanner.close();
    }
}