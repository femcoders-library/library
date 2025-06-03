package org.example.repository;

import org.example.config.DBManager;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

            System.out.println("Book added to the library");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }

    public void updateBookByField(String isbn, Map<String, String> fieldsToUpdate) {
        if (!existByISBN(isbn)) {
            System.out.println("No book was found with this ISBN.");
            return;
        }

        if (fieldsToUpdate == null || fieldsToUpdate.isEmpty()) {
            System.out.println("There are no fields to update.");
            return;
        }

        StringBuilder query = new StringBuilder("UPDATE books SET ");
        List<String> fieldNames = new ArrayList<>();
        for (String field : fieldsToUpdate.keySet()) {
            query.append(field).append(" = ?, ");
            fieldNames.add(field);
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE isbn = ?");

        try {
            connection = DBManager.initConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString());

            int index = 1;
            for (String field : fieldNames) {
                statement.setString(index++, fieldsToUpdate.get(field));
            }
            statement.setString(index, isbn);

            int updated = statement.executeUpdate();
            if (updated > 0) {
                System.out.println("The book was updated successfully.");
            } else {
                System.out.println("Error updating the book.");
            }

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

            System.out.println("Book deleted");
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

    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title = ?";
        try {
            connection = DBManager.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            ResultSet response = preparedStatement.executeQuery();

            while (response.next()) {
                String foundTitle = response.getString("title");
                String synopsis = response.getString("synopsis");
                String isbn = response.getString("isbn");
                String author = response.getString("author");
                String genre = response.getString("genre");

                Book book = new Book(foundTitle, synopsis, isbn, author, genre);
                books.add(book);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return books;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE author LIKE ?";
        try {
            connection = DBManager.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + author + "%");
            ResultSet response = preparedStatement.executeQuery();

            while (response.next()) {
                String title = response.getString("title");
                String synopsis = response.getString("synopsis");
                String isbn = response.getString("isbn");
                String foundAuthor = response.getString("author");
                String genre = response.getString("genre");

                Book book = new Book(title, synopsis, isbn, foundAuthor, genre);
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