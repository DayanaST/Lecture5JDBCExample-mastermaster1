package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Book;
import com.company.repositories.interfaces.IBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB db;

    public BookRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createBook(Book book) {
        String sql = "INSERT INTO books(title, author_id) VALUES (?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, book.getTitle());
            st.setInt(2, book.getAuthorId());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    // Улучшенный метод получения всех книг (сразу с именами авторов)
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.book_id, b.title, b.author_id, u.name as author_name " +
                "FROM books b LEFT JOIN users u ON b.author_id = u.author_id";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("author_id")
                );
                book.setAuthorName(rs.getString("author_name"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
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
