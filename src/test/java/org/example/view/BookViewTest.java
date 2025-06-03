package org.example.view;

import org.example.controller.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookViewTest {

    @Mock
    private BookController bookController;

    private Scanner testScanner;
    private BookView bookView;

    @BeforeEach
    void setUp() {
        String input = String.join("\n",
                "1234567890",
                "title",
                "New Title",
                "save"
        );
        testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        bookView = new BookView(bookController, testScanner); // <- все вірно
    }

    @Test
    void updateBookByField_shouldCallControllerWithCorrectUpdates() {
        when(bookController.existByISBN("1234567890")).thenReturn(true);

        bookView.updateBookByField();

        Map<String, String> expected = new HashMap<>();
        expected.put("title", "New Title");

        verify(bookController).updateBookByField("1234567890", expected);
    }
}