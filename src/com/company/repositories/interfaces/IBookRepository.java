package com.company.repositories.interfaces;

import com.company.models.Book;
import java.util.List;

public interface IBookRepository {
    boolean createBook(Book book);
    List<Book> getAllBooks();
}

