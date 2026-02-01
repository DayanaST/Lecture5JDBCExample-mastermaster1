package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Book;
import com.company.repositories.interfaces.IBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public record BookRepository(IDB db) implements IBookRepository {

    @Override
    public boolean createBook(Book book) {
        String sql = "INSERT INTO books(title, author_id, published_year) VALUES (?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, book.getTitle());
            st.setInt(2, book.getAuthorId());
            st.setInt(3, book.getPublishedYear());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.*, u.name as author_name FROM books b " +
                "LEFT JOIN users u ON b.author_id = u.author_id";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("author_id"),
                        rs.getInt("published_year") // Читаем год из БД
                );
                book.setAuthorName(rs.getString("author_name"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getBooksOnlyWithAuthors() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.book_id, b.title, u.name AS author_name " +
                "FROM books b " +
                "JOIN users u ON b.author_id = u.author_id";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("author_id"), rs.getInt("author_id")
                );
                book.setAuthorName(rs.getString("author_name"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    private String getAuthorNameById(int id) {
        String sql = "SELECT name FROM users WHERE author_id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Unknown";
    }
}