package com.company;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {

        String connectionUrl = "jdbc:postgresql://localhost:5432";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        IDB db = new PostgresDB(
                connectionUrl,
                "postgres",
                "0000",
                "librd"
        );

        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);

        MyApplication app = new MyApplication(controller);
        app.start();

        db.close();
    }
}
