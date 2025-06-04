package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void main_shouldNotThrowWithMockedInput() {
        String input = "8\n";  // Одразу виходимо з меню
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            assertDoesNotThrow(() -> Main.main(new String[]{}));
        } finally {
            System.setIn(originalIn);
        }
    }
}