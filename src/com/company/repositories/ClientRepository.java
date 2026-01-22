package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.Client;
import com.company.repositories.interfaces.IClientRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IClientRepository {
    private final IDB db;
    public ClientRepository(IDB db) { this.db = db; }

    @Override
    public boolean createClient(Client client) {
        String sql = "INSERT INTO clients(first_name, last_name, rec_date) VALUES (?, ?, CURRENT_TIMESTAMP)";

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, client.getFirstName());
            st.setString(2, client.getLastName());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection con = db.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM clients")) {
            while (rs.next()) {
                clients.add(new Client(rs.getInt("client_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getTimestamp("rec_date"), rs.getTimestamp("pass_date")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return clients;
    }
}