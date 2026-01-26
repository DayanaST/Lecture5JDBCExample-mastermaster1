package com.company.data;
import com.company.data.interfaces.IDB;

public class DBInstance {
    private static IDB instance;

    private DBInstance() {}

    public static IDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB(
                    "jdbc:postgresql://localhost:5432",
                    "postgres",
                    "0000",
                    "librd"
            );
        }
        return instance;
    }
}
