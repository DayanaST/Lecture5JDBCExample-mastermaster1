package com.company.repositories;
import com.company.data.interfaces.IDB;
import com.company.models.Book;
import com.company.repositories.interfaces.IBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB db;

    public BookRepository(IDB db) { this.db = db; }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sqlBooks = "SELECT book_id, title,author_id FROM books";
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sqlBooks)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("author_id")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (Book book : books) {
            book.setAuthorName(getAuthorNameById(book.getAuthorId()));
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

