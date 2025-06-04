package org.example.repository;

import org.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void updateBookByField_shouldUpdateSpecifiedFields() {
        Book book = new Book("Original Title", "Original Synopsis", "1111111111111", "Original Author", "Original Genre");
        bookRepository.addBook(book);

        Map<String, String> updates = new HashMap<>();
        updates.put("title", "Updated Title");
        updates.put("author", "Updated Author");

        bookRepository.updateBookByField(book.getIsbn(), updates);

        List<Book> updatedBooks = bookRepository.findByTitle("Updated Title");
        assertFalse(updatedBooks.isEmpty());

        Book updatedBook = updatedBooks.stream()
                .filter(b -> b.getIsbn().equals(book.getIsbn()))
                .findFirst()
                .orElse(null);

        assertNotNull(updatedBook);
        assertEquals("Updated Title", updatedBook.getTitle());
        assertEquals("Updated Author", updatedBook.getAuthor());

        bookRepository.deleteBook(book.getIsbn());
    }

    @Test
    void updateBookByField_shouldIgnoreInvalidFieldNames() {
        Book book = new Book("Original Title", "Original Synopsis", "1111111111111", "Original Author", "Original Genre");
        bookRepository.addBook(book);

        Map<String, String> updates = new HashMap<>();
        updates.put("nonexistent_field", "Value");

        assertDoesNotThrow(() -> bookRepository.updateBookByField(book.getIsbn(), updates));

        bookRepository.deleteBook(book.getIsbn());
    }
}
