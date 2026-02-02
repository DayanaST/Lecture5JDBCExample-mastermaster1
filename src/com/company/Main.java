package com.company;

import com.company.controllers.UserController;
import com.company.controllers.FeedbackController;
import com.company.controllers.interfaces.IFeedbackController;
import com.company.data.DBInstance;
import com.company.data.interfaces.IDB;
import com.company.factories.RepositoryFactory;
import com.company.repositories.FeedbackRepository;
import com.company.repositories.interfaces.*;

public class Main {

    public static void main(String[] args) {
        IDB db = DBInstance.getInstance();

        IUserRepository userRepo = RepositoryFactory.createUserRepo(db);
        IBookRepository bookRepo = RepositoryFactory.createBookRepo(db);
        IClientRepository clientRepo = RepositoryFactory.createClientRepo(db);
        ICategoryRepository categoryRepo = RepositoryFactory.createCategoryRepo(db);

        IFeedbackRepository feedbackRepo = new FeedbackRepository(db);
        UserController userController = new UserController(
                userRepo,
                bookRepo,
                clientRepo,
                categoryRepo);

        IFeedbackController feedbackController = new FeedbackController(feedbackRepo);
        MyApplication app = new MyApplication(userController, feedbackController);

        System.out.println("Connecting to database and starting application...");

        app.start();

        db.close();
    }
}

