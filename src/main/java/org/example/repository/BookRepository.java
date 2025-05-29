package org.example.repository;
import org.example.config.DBManager;
import org.example.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.config.DBManager.connection;

public class BookRepository {

    private Connection connection;

    public void addBook(Book book) {
        String querySQLCreate = "INSERT INTO books (title, synopsis, isbn, author, genre) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = DBManager.initConnection();
            PreparedStatement statement = connection.prepareStatement(querySQLCreate);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getSynopsis());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getAuthor());
            statement.setString(5, book.getGenre());

            statement.execute();

            System.out.println("Libro añadido a la librería");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }

    // Example method to retrieve a book by its ID
    public void getBookById(int id) {
        // Implementation for retrieving a book from the database by its ID
    }

    // Additional methods for updating and deleting books can be added here

    // Example method to update a book
    public void updateBook(int id, String title, String synopsis, String isbn, String author, String genre) {
        // Implementation for updating a book in the database

    }

    // Example method to delete a book
    public void deleteBook(int id) {
        // Implementation for deleting a book from the database
    }

    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";   // Example SQL query to retrieve all books
        try {
            connection = DBManager.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet response = preparedStatement.executeQuery();
            while (response.next()) {
                String title = response.getString("title");
                String synopsis = response.getString("synopsis");
                String isbn = response.getString("isbn");
                String author = response.getString("author");
                String genre = response.getString("genre");

                Book book = new Book(title, synopsis, isbn, author, genre);
                books.add(book);

            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return books;
    }
}