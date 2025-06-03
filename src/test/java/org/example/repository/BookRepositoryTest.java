package org.example.repository;

import org.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BookRepositoryTest {
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    void findAllBooks_shouldNotBeEmpty_afterCreateABook() {
        Book book = new Book("El Alquimista", "Una novela que narra la historia de un joven pastor llamado Santiago que busca su leyenda personal y el significado de la vida.", "978-3-16-148410-0", "Paulo Coelho", "Ficción");

        bookRepository.addBook(book);
        List<Book> books = bookRepository.findAllBooks();

        assertFalse(books.isEmpty(), "The list of all books shouldn't be empty");
    }
}
