package org.example.view;

import org.example.controller.BookController;
import org.example.model.Book;
import org.example.util.AnsiStyle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookView {
    private final BookController bookController;
    private Scanner scanner;

    public BookView(BookController bookController, Scanner scanner) {
        this.bookController = bookController;
        this.scanner = scanner;

    }

    public void createBook() {
        Book book = generateBook();
        bookController.createBook(book);
    }

    public void updateBookByField() {
        System.out.println("\nEnter the ISBN of the book you want to update:");
        String isbn = scanner.nextLine();

        if (!bookController.existByISBN(isbn)) {
            System.out.println(AnsiStyle.stylingText("\nNo book exists with this ISBN.", AnsiStyle.RED));
            return;
        }

        Map<String, String> fieldsToUpdate = new HashMap<>();
        while (true) {
            System.out.println("\nWhich field do you want to update? (title, synopsis, isbn, author, genre): ");
            System.out.println("\nType “save” to finish updating.");
            String field = scanner.nextLine().toLowerCase();

            if (field.equals("save")) break;

            if (!List.of("title", "synopsis", "isbn", "author", "genre").contains(field)) {
                System.out.println(AnsiStyle.stylingText("\nInvalid field.", AnsiStyle.RED));
                continue;
            }

            System.out.println("\nEnter a new value for " + field + ":");
            String value = scanner.nextLine();
            fieldsToUpdate.put(field, value);
        }

        bookController.updateBookByField(isbn, fieldsToUpdate);
    }

    public void deleteBook() {
        System.out.println("\nEnter the ISBN of the book you want to remove from the library: ");
        String isbn = scanner.nextLine();
        bookController.deleteBook(isbn);
    }

    public Book generateBook() {
        System.out.print("\nEnter the book's title: ");
        String title = scanner.nextLine();
        System.out.print("\nEnter the book's synopsis.: ");
        String synopsis = scanner.nextLine();
        System.out.print("\nEnter the book's ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("\nEnter the book's author: ");
        String author = scanner.nextLine();
        System.out.print("\nEnter the book's genre: ");
        String genre = scanner.nextLine();

        Book book = new Book(title, synopsis, isbn, author, genre);

        return book;
    }


    public void displayBooks() {
        for (Book book : bookController.getAllBooks()) {
            System.out.println(book);
        }
    }

    public void findByTitle() {
        System.out.print("\nEnter the title of the book to search for: ");
        String title = scanner.nextLine();

        List<Book> foundBooks = bookController.findByTitle(title);

        if (foundBooks.isEmpty()) {
            System.out.println(AnsiStyle.stylingText("\nNo books found with that title.", AnsiStyle.RED));
        } else {
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }

    public void findByAuthor() {
        System.out.print("\nEnter the author of the book to search:");
        String author = scanner.nextLine();

        List<Book> foundBooks = bookController.findByAuthor(author);

        if (foundBooks.isEmpty()) {
            System.out.println(AnsiStyle.stylingText("\nNo books found by that author.", AnsiStyle.RED));
        } else {
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }

    public void findByGenre() {
        System.out.print("\nEnter the genre of the book to search:");
        String genre = scanner.nextLine();

        List<Book> foundBooks = bookController.findByGenre(genre);

        if (foundBooks.isEmpty()) {
            System.out.println(AnsiStyle.stylingText("\nNo books found by that genre.", AnsiStyle.RED));
        } else {
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println(AnsiStyle.stylingText("\n=== Library Menu ===\n", AnsiStyle.BG_YELLOW, AnsiStyle.UNDERLINE, AnsiStyle.BOLD));
            System.out.println("1. View all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Edit a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Search book by title");
            System.out.println("6. Search book by author");
            System.out.println("7. Search book by genre");
            System.out.println("8. Exit");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("-----------------------------");
                    displayBooks();
                    break;
                case "2":
                    System.out.println("-----------------------------");
                    createBook();
                    break;
                case "3":
                    System.out.println("-----------------------------");
                    updateBookByField();
                    break;
                case "4":
                    System.out.println("-----------------------------");
                    deleteBook();
                    break;
                case "5":
                    System.out.println("-----------------------------");
                    findByTitle();
                    break;
                case "6":
                    System.out.println("-----------------------------");
                    findByAuthor();
                    break;
                case "7":
                    System.out.println("-----------------------------");
                    findByGenre();
                    break;
                case "8":
                    System.out.println("\nBye! Thanks for using our library!");
                    running = false;
                    break;
                default:
                    System.out.println(AnsiStyle.stylingText("\nInvalid option. Please try again.", AnsiStyle.RED));
            }
        }
        scanner.close();
    }
}
