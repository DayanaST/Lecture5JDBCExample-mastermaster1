package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.User;
import com.company.models.Role;
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
        try (Connection con = db.getConnection()) {

            String sql = "INSERT INTO users(name) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());

            int rows = st.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT author_id, name FROM users WHERE author_id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        Role.USER   // пока по умолчанию
                );

            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT author_id, name FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("author_id"),
                        rs.getString("name"));

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }
}
