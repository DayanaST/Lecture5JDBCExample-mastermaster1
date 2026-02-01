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

    public UserController(IUserRepository userRepo,
                          IBookRepository bookRepo,
                          IClientRepository clientRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
    }

    private boolean isValidString(String text) {
        return text != null && text.trim().length() >= 2;
    }

    private boolean hasPermission(User user) {
        return user != null && user.getRole() == Role.ADMIN;
    }

    public String addBook(User user, String title, int authorId) {
        if (!hasPermission(user)) {
            return "Access denied";
        }

        if (!isValidString(title)  authorId <= 0) {
            return "Invalid input data";
        }

        return bookRepo.createBook(new Book(0, title, authorId))
                ? "Book added successfully"
                : "Failed to add book";
    }

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public boolean addAuthor(String name) {
        if (!isValidString(name)) {
            return false;
        }
        return userRepo.createUser(new User(name, Role.AUTHOR));
    }

    public String addClient(String fName, String lName) {
        if (!isValidString(fName)  !isValidString(lName)) {
            return "Invalid input data";
        }

        return clientRepo.createClient(new Client(fName, lName))
                ? "Client added"
                : "Failed";
    }

    public List<Client> getAllClients() {
        return clientRepo.getAllClients();
    }
}