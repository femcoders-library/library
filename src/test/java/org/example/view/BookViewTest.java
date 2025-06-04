package org.example.view;

import org.example.controller.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookViewTest {
    @Mock
    private BookController bookController;

    private Scanner testScanner;
    private BookView bookView;

    @Test
    void updateBookByField_shouldCallControllerWithCorrectUpdates() {
        String input = String.join("\n",
                "1234567890",
                "title",
                "New Title",
                "save"
        );
        testScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        bookView = new BookView(bookController, testScanner);

        when(bookController.existByISBN("1234567890")).thenReturn(true);

        bookView.updateBookByField();

        Map<String, String> expected = new HashMap<>();
        expected.put("title", "New Title");

        verify(bookController).updateBookByField("1234567890", expected);
    }

    @Test
    void showMenu_shouldManageInvalidInput(){
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        String simulatedInvalidInput = "abc\n8\n";

        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInvalidInput.getBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        try {
            System.setIn(testIn);
            System.setOut(new PrintStream(testOut));

            bookView = new BookView(bookController, new Scanner(System.in));

            bookView.showMenu();

            String output = testOut.toString();

            assertTrue(output.contains("Invalid option. Please try again"));
            assertTrue(output.contains("Bye!"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
