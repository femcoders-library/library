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
        System.out.println("Introduce el ISBN del libro que quieras actualizar: ");
        String isbn = scanner.nextLine();

        if (!bookController.existByISBN(isbn)) {
            System.out.println("No existe ningún libro con el ISBN introducido");
            return;
        }

        Book book = generateBookWithoutISBN();
        book.setIsbn(isbn);
        bookController.updateBook(isbn, book);
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
            System.out.println("Título: " + book.getTitle());
            System.out.println("Sinopsis: " + book.getSynopsis());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Autor/a/es/as: " + book.getAuthor());
            System.out.println("Género/S: " + book.getGenre());
            System.out.println("-----------------------------");
        }
    }
}
