package com.company;

import com.company.controllers.FeedbackController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IFeedbackController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.BookRepository;
import com.company.repositories.FeedbackRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.ClientRepository;
import com.company.repositories.interfaces.IBookRepository;
import com.company.repositories.interfaces.IFeedbackRepository;
import com.company.repositories.interfaces.IUserRepository;
import com.company.repositories.interfaces.IClientRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = DBInstance.getInstance();
        (
                "jdbc:postgresql://localhost:5432",
                "postgres",
                "0000",
                "librd"
        );

        IUserRepository userRepo = RepositoryFactory.createUserRepo(db);
        IBookRepository bookRepo = RepositoryFactory.createBookRepo(db);
        IClientRepository clientRepo = RepositoryFactory.createClientRepo(db);
        IFeedbackRepository feedRepo = com.company.factories.RepositoryFactory.createFeedbackRepo(db);
        IFeedbackController feedbackController = new FeedbackController(feedbackRepo);

        UserController controller = new UserController(userRepo, bookRepo, clientRepo);
        MyApplication app = new MyApplication(controller, feedbackController);

        app.start();
        db.close();
    }
}


