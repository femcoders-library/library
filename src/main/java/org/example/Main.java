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
            System.out.println("\n=== Librería Menu ===\n");
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Añadir un nuevo libro");
            System.out.println("3. Editar un libro");
            System.out.println("4. Eliminar un libro");
            System.out.println("5. Buscar libro/s por título");
            System.out.println("6. Buscar libro/s por autor");
            System.out.println("7. Salir");
            System.out.print("\nSeleccciona una opción: ");

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
                    System.out.println("\nAdiós!");
                    running = false;
                    break;
                default:
                    System.out.println("\nOpción inválida. Por favor inténtalo de nuevo.");
            }
        }
        scanner.close();
    }
}