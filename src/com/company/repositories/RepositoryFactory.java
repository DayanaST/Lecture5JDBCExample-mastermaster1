package com.company.factories;

import com.company.data.interfaces.IDB;
import com.company.repositories.*;
import com.company.repositories.interfaces.*;

public class RepositoryFactory {

    public static IUserRepository createUserRepo(IDB db) {
        return new UserRepository(db);
    }

    public static IBookRepository createBookRepo(IDB db) {
        return new BookRepository(db);
    }

    public static IClientRepository createClientRepo(IDB db) {
        return new ClientRepository(db);
    }
}
