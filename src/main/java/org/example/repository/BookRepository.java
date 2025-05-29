package org.example.repository;

import org.example.config.DBManager;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void updateBook(String isbn, Book book) {
        if (!existByISBN(isbn)) {
            return;
        }

        String querySQLUpdate = "UPDATE books SET title=?, author=?, synopsis=?, genre=? WHERE isbn=?";

        try {
            connection = DBManager.initConnection();
            PreparedStatement statement = connection.prepareStatement(querySQLUpdate);

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getSynopsis());
            statement.setString(4, book.getGenre());
            statement.setString(5, book.getIsbn());

            statement.executeUpdate();

            System.out.println("Libro actualizado");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }

    public void deleteBook(String isbn) {
        if (!existByISBN(isbn)) {
            return;
        }
        String querySQLDelete = "DELETE FROM books WHERE isbn = ?";
        try {
            connection = DBManager.initConnection();
            PreparedStatement statement = connection.prepareStatement(querySQLDelete);
            statement.setString(1, isbn);

            statement.executeUpdate();

            System.out.println("Libro eliminado");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }

    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
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

    public boolean existByISBN(String isbn) {
        String sql = "SELECT COUNT(*) FROM books WHERE isbn = ?";

        try {
            connection = DBManager.initConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, isbn);
            ResultSet response = statement.executeQuery();
            response.next();

            return response.getInt(1) > 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return false;
    }
}