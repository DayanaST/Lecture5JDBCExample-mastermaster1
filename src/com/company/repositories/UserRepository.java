package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Role;
import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO users(name) VALUES (?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, user.getName()
            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("SQL error:
            return false;
        }
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT author_id, name FROM users WHERE author_id=?";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("author_id"),
                            rs.getString("name"),
                            Role.USER
                    );
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT author_id, name FROM users";
        List<User> users = new ArrayList<>();

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        Role.USER
                ));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return null;
        }
    }
}
