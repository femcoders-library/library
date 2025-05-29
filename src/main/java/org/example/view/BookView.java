package org.example.view;

import org.example.controller.BookController;
import org.example.model.Book;

import java.util.Scanner;

public class BookView {
    private final BookController bookController;
    private Scanner scanner = new Scanner(System.in);

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public void createBook() {
        Book book = generateBook();
        bookController.createBook(book);
    }

    public void updateBook() {
        System.out.println("Put isbn: ");
        String isbn = scanner.nextLine();

        if (!bookController.existByISBN(isbn)) {
            System.out.println("Exit with such isbn");
            return;
        }

        Book book = generateBookWithoutISBN();
        book.setIsbn(isbn);
        bookController.updateBook(isbn, book);
    }

    public Book generateBook() {
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

        Book book = new Book(title, synopsis, isbn, author, genre);

        return book;
    }

    public Book generateBookWithoutISBN() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book synopsis: ");
        String synopsis = scanner.nextLine();
        //String isbn = null;
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();

        Book book = new Book(title, synopsis, null, author, genre);

        return book;
    }

    public void displayBooks() {

        for (Book book : bookController.getAllBooks()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Synopsis: " + book.getSynopsis());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("-----------------------------");
        }
    }
}
