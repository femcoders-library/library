package org.example.controller;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    private Book book;
    private String isbn;

    @BeforeEach
    void setUp() {
        book = new Book("El Alquimista", "Una novela que narra la historia de un joven pastor llamado Santiago que busca su leyenda personal y el significado de la vida.", "978-3-16-148410-0", "Paulo Coelho", "Ficción");
        isbn = "978-3-16-148410-0";
    }

    @Test
    void createBook_shouldCallRepository() {
        bookController.createBook(book);
        verify(bookRepository, times(1)).addBook(book);
    }

    @Test
    void updateBookByField_shouldCallRepository_withCorrectFields() {
        Map<String, String> updates = new HashMap<>();
        updates.put("title", "New title");

        bookController.updateBookByField(isbn, updates);
        verify(bookRepository).updateBookByField(isbn, updates);
    }

    @Test
    void deleteBook_shouldCallRepository() {
        bookController.deleteBook(isbn);
        verify(bookRepository, times(1)).deleteBook(isbn);
    }

    @Test
    void findAllBooks_shouldCallRepository() {
        bookController.getAllBooks();
        verify(bookRepository, times(1)).findAllBooks();
    }

    @Test
    void existByISBN_shouldCallRepository() {
        bookController.existByISBN(isbn);
        verify(bookRepository, times(1)).existByISBN(isbn);
    }

    @Test
    void findByTitle_shouldCallRepository() {
        bookController.findByTitle(book.getTitle());
        verify(bookRepository, times(1)).findByTitle(book.getTitle());
    }

    @Test
    void findByAuthor_shouldCallRepository() {
        bookController.findByAuthor(book.getAuthor());
        verify(bookRepository, times(1)).findByAuthor(book.getAuthor());
    }

    @Test
    void findByGenre_shouldCallRepository() {
        bookController.findByGenre(book.getGenre());
        verify(bookRepository, times(1)).findByGenre(book.getGenre());
    }
}