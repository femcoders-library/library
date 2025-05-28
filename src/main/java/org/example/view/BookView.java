package org.example.view;

import org.example.controller.BookController;
import org.example.model.Book;

import java.util.Scanner;

public class BookView {
    private final BookController bookController;
    public BookView(BookController bookController) {
        this.bookController = bookController;
    }
    public void createBook(String title, String synopsis, String isbn, String author, String genre) {
        bookController.createBook(title, synopsis, isbn, author, genre);
    }
    public Book generateBook() {
        Scanner scanner = new Scanner(System.in);
        // This method will generate a book object based on user input
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book synopsis: ");
        String synopsis = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        // Create a new Book object with the provided details
        Book book = new Book();
        book.setTitle(title);
        book.setSynopsis(synopsis);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setGenre(genre);
        scanner.close();
        // Return the newly created book object
        return book;
    }

    public void displayBooks() {
        // This method will display all books in the library
        for (Book book : bookController.getAllBooks()) {
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Synopsis: " + book.getSynopsis());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("-----------------------------");
        }
    }
}
