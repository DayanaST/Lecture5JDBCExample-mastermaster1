package com.company.controllers;

import com.company.models.*;
import com.company.repositories.interfaces.*;
import java.util.List;

public class UserController {
    private final IUserRepository userRepo;
    private final IBookRepository bookRepo;
    private final IClientRepository clientRepo;
    private final ICategoryRepository categoryRepo;

    public UserController(IUserRepository userRepo, IBookRepository bookRepo,
                          IClientRepository clientRepo, ICategoryRepository categoryRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
        this.categoryRepo = categoryRepo;
    }

    private boolean isValidString(String text) {
        return text != null && text.trim().length() >= 2;
    }

    private boolean hasPermission(User user) {
        return user.getRole() == Role.ADMIN || user.getRole() == Role.MANAGER;
    }

    public String addBook(User user, String title, int authorId, int year, int categoryId) {
        if (!hasPermission(user)) return "Access denied";
        if (!isValidString(title)  authorId <= 0  categoryId <= 0) {
            return "Invalid input data: check title, author or category ID";
        }
        Book newBook = new Book(0, title, authorId, year, categoryId);

        return bookRepo.createBook(newBook)
                ? "Book added successfully with category!"
                : "Failed to add book";
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public boolean addAuthor(String name) {
        if (!isValidString(name)) return false;
        return userRepo.createUser(new User(0, name, Role.USER));
    }

    public String addClient(String fName, String lName) {
        if (!isValidString(fName) || !isValidString(lName)) return "Invalid input data";
        return clientRepo.createClient(new Client(fName, lName)) ? "Client added" : "Failed";
    }

    public List<Client> getAllClients() {
        return clientRepo.getAllClients();
    }

    public List<Category> getAllCategories() {
        return categoryRepo.getAllCategories();
    }

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }
}
