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

    public void updateBook() {
        System.out.println("Introduce el ISBN del libro que quieras actualizar: ");
        String isbn = scanner.nextLine();

        if (!bookController.existByISBN(isbn)) {
            System.out.println("No existe ningún libro con el ISBN introducido");
            return;
        }

        Book book = generateBookWithoutISBN();
        bookController.updateBook(isbn, book);
    }

    public void updateBookByField() {
        System.out.println("Introduzca el ISBN del libro que desea actualizar:");
        String isbn = scanner.nextLine();

        if (!bookController.existByISBN(isbn)) {
            System.out.println("No existe un libro con este ISBN.");
            return;
        }

        Map<String, String> fieldsToUpdate = new HashMap<>();
        while (true) {
            System.out.println("¿Qué campo quieres actualizar? (title, synopsis, author, genre): ");
            System.out.println("Escriba “exit” para salir.");
            String field = scanner.nextLine().toLowerCase();

            if (field.equals("exit")) break;

            if (!List.of("title", "synopsis", "author", "genre").contains(field)) {
                System.out.println("Campo inválido.");
                continue;
            }

            System.out.println("Introduzca un nuevo valor para " + field + ":");
            String value = scanner.nextLine();
            fieldsToUpdate.put(field, value);
        }

        bookController.updateBookByField(isbn, fieldsToUpdate);
    }

    public void deleteBook() {
        System.out.println("Introduce el ISBN del libro que quieras eliminar de la librería: ");
        String isbn = scanner.nextLine();
        bookController.deleteBook(isbn);
    }

    public Book generateBook() {
        System.out.print("Introduce el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Introduce la sinopsis del libro: ");
        String synopsis = scanner.nextLine();
        System.out.print("Introduce el ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Introduce el/la/los/las autor/a/es/as del libro: ");
        String author = scanner.nextLine();
        System.out.print("Introduce el/los género/s del libro: ");
        String genre = scanner.nextLine();

        Book book = new Book(title, synopsis, isbn, author, genre);

        return book;
    }

    public Book generateBookWithoutISBN() {
        System.out.print("Introduce el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Introduce la sinopsis del libro: ");
        String synopsis = scanner.nextLine();
        System.out.print("Introduce el/la/los/las autor/a/es/as del libro: ");
        String author = scanner.nextLine();
        System.out.print("Introduce el/los género/s del libro: ");
        String genre = scanner.nextLine();

        Book book = new Book(title, synopsis, null, author, genre);

        return book;
    }

    public void displayBooks() {

        for (Book book : bookController.getAllBooks()) {
/*            System.out.println("Título: " + book.getTitle());
            System.out.println("Sinopsis: " + book.getSynopsis());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Autor/a/es/as: " + book.getAuthor());
            System.out.println("Género/S: " + book.getGenre());
            System.out.println("-----------------------------");*/

            System.out.printf("""
                    \nTítulo: %s
                    ISBN: %s
                    Autor/a/es/as: %s
                    Género/s: %s
                    \n-----------------------------
                    """, book.getTitle(), book.getIsbn(), book.getAuthor(), book.getGenre());

        }
    }

    public void findByTitle() {
        System.out.print("Introduce el título del libro a buscar: ");
        String title = scanner.nextLine();

        List<Book> foundBooks = bookController.findByTitle(title);

        if (foundBooks.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            for (Book book : foundBooks) {
                System.out.printf("""
                    \nTítulo: %s
                    Sinopsis: %s
                    ISBN: %s
                    Autor/a/es/as: %s
                    Género/s: %s
                    \n-----------------------------
                    """, book.getTitle(), book.getSynopsis(), book.getIsbn(), book.getAuthor(), book.getGenre());
            }
        }
    }

    public void findByAuthor() {
        System.out.print("Introduce el título del libro a buscar: ");
        String author = scanner.nextLine();

        List<Book> foundBooks = bookController.findByAuthor(author);

        if (foundBooks.isEmpty()) {
            System.out.println("No se encontraron libros con ese/a/os/as autor/a/es/as.");
        } else {
            for (Book book : foundBooks) {
                System.out.printf("""
                    \nTítulo: %s
                    Sinopsis: %s
                    ISBN: %s
                    Autor/a/es/as: %s
                    Género/s: %s
                    \n-----------------------------
                    """, book.getTitle(), book.getSynopsis(), book.getIsbn(), book.getAuthor(), book.getGenre());
            }
        }
    }

}
