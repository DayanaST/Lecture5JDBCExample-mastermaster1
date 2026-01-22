package com.company.controllers;

import com.company.models.Book;
import com.company.models.User;
import com.company.repositories.BookRepository;
import com.company.repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController {

    private final IUserRepository userRepo;
    private final BookRepository bookRepo;

    public UserController(IUserRepository userRepo, BookRepository bookRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public boolean addAuthor(String name) {
        return userRepo.createUser(new User(name));
    }

    public boolean addBook(String title, int authorId) {
        return bookRepo.createBook(new Book(0, title, authorId));
    }
}

