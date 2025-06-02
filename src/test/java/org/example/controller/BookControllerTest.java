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
}