package org.example;
import org.example.controller.BookController;
import org.example.repository.BookRepository;
import org.example.view.BookView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookController bookController = new BookController(bookRepository);
        BookView bookView = new BookView(bookController);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Library Menu ===\n");
            System.out.println("1. View all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Edit a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Find book/s by title");
            System.out.println("6. Find book/s by author");
            System.out.println("7. Exit");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("-----------------------------");
                    bookView.displayBooks();
                    break;
                case "2":
                    System.out.println("-----------------------------");
                    bookView.createBook();
                    break;
                case "3":
                    // Uncomment the one you use
                    // bookView.updateBook();
                    System.out.println("-----------------------------");
                    bookView.updateBookByField();
                    break;
                case "4":
                    System.out.println("-----------------------------");
                    bookView.deleteBook();
                    break;
                case "5":
                    System.out.println("-----------------------------");
                    bookView.findByTitle();
                    break;
                case "6":
                    System.out.println("-----------------------------");
                    bookView.findByAuthor();
                    break;
                case "7":
                    System.out.println("\nGoodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
        scanner.close();
    }
}