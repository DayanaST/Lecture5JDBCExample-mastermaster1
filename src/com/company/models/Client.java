package com.company.models;

import java.sql.Timestamp;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp recDate;
    private Timestamp passDate;

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(int id, String firstName, String lastName, Timestamp recDate, Timestamp passDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.recDate = recDate;
        this.passDate = passDate;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    @Override
    public String toString() {
        return "Client{id=" + id + ", name='" + firstName + " " + lastName + "', registered=" + recDate + "}";
    }
}