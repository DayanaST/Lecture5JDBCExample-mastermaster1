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
        String sql = "INSERT INTO books(title, author_id, published_year, category_id) VALUES (?, ?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, book.getTitle());
            st.setInt(2, book.getAuthorId());
            st.setInt(3, book.getPublishedYear());
            st.setInt(4, book.getCategoryId());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.*, u.name as author_name, c.name as cat_name " +
                "FROM books b " +
                "LEFT JOIN users u ON b.author_id = u.author_id " +
                "LEFT JOIN categories c ON b.category_id = c.id";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getInt("author_id"),
                        rs.getInt("published_year")
                );
                book.setCategoryName(rs.getString("cat_name"));
                books.add(book);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
    }
    public List<Book> getBooksByAuthor(int authorId) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author_id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, authorId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getInt("author_id"),
                            rs.getInt("published_year")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return books;
    }
}