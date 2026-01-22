package com.company.controllers;

import com.company.models.Book;
import com.company.models.User;
import com.company.repositories.BookRepository;
import com.company.repositories.interfaces.IBookRepository;
import com.company.repositories.interfaces.IUserRepository;
import java.util.List;

public class UserController {
    private final IUserRepository userRepo;
    private final IBookRepository bookRepo;

    public UserController(IUserRepository userRepo, IBookRepository bookRepo) {
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

    public boolean addReview(int bookId, Object clientName, String comment) {
        return false;
    }
}
