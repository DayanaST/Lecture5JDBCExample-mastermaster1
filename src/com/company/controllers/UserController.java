package com.company.controllers;

import com.company.models.Book;
import com.company.models.User;
import com.company.models.Client;
import com.company.models.Role;
import com.company.repositories.interfaces.IBookRepository;
import com.company.repositories.interfaces.IUserRepository;
import com.company.repositories.interfaces.IClientRepository;
import java.util.List;

public class UserController {
    private final IUserRepository userRepo;
    private final IBookRepository bookRepo;
    private final IClientRepository clientRepo;

    public UserController(IUserRepository userRepo, IBookRepository bookRepo, IClientRepository clientRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
    }
    private boolean isValidString(String text) {
        return text != null && text.trim().length() >= 2;
    }

    public String addBook(String title, int authorId) {
        ...
    }
}
    private boolean hasPermission(User user) {
        return user.getRole() == Role.ADMIN || user.getRole() == Role.MANAGER;
    }


    public List<Book> getAllBooks() { return bookRepo.getAllBooks(); }
    public List<User> getAllUsers() { return userRepo.getAllUsers(); }

public String addBook(String title, int authorId) {
    if (!isValidString(title) || authorId <= 0) {
        return "Invalid input";
    }
    return bookRepo.createBook(new Book(0, title, authorId))
            ? "Success!" : "Failed!";
}
public String addClient(String fName, String lName) {
    if (!isValidString(fName) || !isValidString(lName)) {
        return "Invalid input data";
    }
    return clientRepo.createClient(new Client(fName, lName))
            ? "Client added!" : "Failed!";
}

    public List<Client> getAllClients() { return clientRepo.getAllClients(); }

    public boolean addAuthor(String name) {
        return false;
    }
}