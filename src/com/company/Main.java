package com.company;

import com.company.controllers.UserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.BookRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB(
                "jdbc:postgresql://localhost:5432",
                "postgres",
                "0000",
                "librd"
        );

        IUserRepository userRepo = new UserRepository(db);
        IBookRepository bookRepo = new BookRepository(db);
        IClientRepository clientRepo = new ClientRepository(db);

        UserController controller =
                new UserController(userRepo, bookRepo, clientRepo);

        MyApplication app = new MyApplication(controller);

        app.start();
    }
}
