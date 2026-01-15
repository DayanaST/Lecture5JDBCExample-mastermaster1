package com.company.controllers;

import com.company.models.User;
import com.company.controllers.interfaces.IUserController;
import com.company.repositories.interfaces.IUserRepository;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public String createUser(String name) {
        User user = new User(name);
        boolean created = repo.createUser(user);
        return (created ? "User was created!" : "User creation was failed!");
    }

    public String getUser(int id) {
        User user = repo.getUser(id);
        return (user == null ? "User was not found!" : user.toString());
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();

        if (users == null) {
            return "Error: Could not fetch users. Check if table exists!";
        }

        if (users.isEmpty()) {
            return "No users found in the database.";
        }
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }

        return response.toString();
    }
}
