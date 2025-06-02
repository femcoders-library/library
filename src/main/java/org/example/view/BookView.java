package org.example.view;

import org.example.controller.BookController;
import org.example.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void updateBookByField() {
        System.out.println("Enter the ISBN of the book you want to update:");
        String isbn = scanner.nextLine();

        if (!bookController.existByISBN(isbn)) {
            System.out.println("No book exists with this ISBN.");
            return;
        }

        Map<String, String> fieldsToUpdate = new HashMap<>();
        while (true) {
            System.out.println("Which field do you want to update? (title, synopsis, author, genre): ");
            System.out.println("Type “exit” to quit.");
            String field = scanner.nextLine().toLowerCase();

            if (field.equals("save")) break;

            if (!List.of("title", "synopsis", "author", "genre").contains(field)) {
                System.out.println("Invalid field.");
                continue;
            }

            System.out.println("Enter a new value for " + field + ":");
            String value = scanner.nextLine();
            fieldsToUpdate.put(field, value);
        }

        bookController.updateBookByField(isbn, fieldsToUpdate);
    }

    public void deleteBook() {
        System.out.println("Enter the ISBN of the book you want to remove from the library: ");
        String isbn = scanner.nextLine();
        bookController.deleteBook(isbn);
    }

    public Book generateBook() {
        System.out.print("Enter the book's title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book's synopsis.: ");
        String synopsis = scanner.nextLine();
        System.out.print("Enter the book's ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter the book's author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the book's genre: ");
        String genre = scanner.nextLine();

        Book book = new Book(title, synopsis, isbn, author, genre);

        return book;
    }


    public void displayBooks() {

        for (Book book : bookController.getAllBooks()) {
            System.out.printf("""
                    \nTitle: %s
                    ISBN: %s
                    Author: %s
                    Genre: %s
                    \n-----------------------------
                    """, book.getTitle(), book.getIsbn(), book.getAuthor(), book.getGenre());

        }
    }

    public void findByTitle() {
        System.out.print("Enter the title of the book to search for: ");
        String title = scanner.nextLine();

        List<Book> foundBooks = bookController.findByTitle(title);

        if (foundBooks.isEmpty()) {
            System.out.println("No books found with that title.");
        } else {
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }

    public void findByAuthor() {
        System.out.print("Enter the title of the book to search:");
        String author = scanner.nextLine();

        List<Book> foundBooks = bookController.findByAuthor(author);

        if (foundBooks.isEmpty()) {
            System.out.println("No books found by that author.");
        } else {
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }

}
