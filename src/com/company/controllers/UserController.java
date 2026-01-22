package com.company.controllers;

import com.company.models.Book;
import com.company.models.User;
import com.company.models.Client;
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

    public List<Book> getAllBooks() { return bookRepo.getAllBooks(); }
    public List<User> getAllUsers() { return userRepo.getAllUsers(); }

    public String addBook(String title, int authorId) {
        return bookRepo.createBook(new Book(0, title, authorId)) ? "Success!" : "Failed!";
    }

    public String addClient(String fName, String lName) {
        return clientRepo.createClient(new Client(fName, lName)) ? "Client added!" : "Failed!";
    }

    public List<Client> getAllClients() { return clientRepo.getAllClients(); }

    public boolean addAuthor(String name) {
        return false;
    }
}