package com.p3lj2.util;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInstance {
    private static DatabaseInstance databaseInstance;
    private static Connection connection;

    private DatabaseInstance(Connection connection) {
        this.connection = connection;
    }

    public static DatabaseInstance getDatabaseInstance() {
        if (databaseInstance == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/9254?user=root&password=");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            databaseInstance = new DatabaseInstance(connection);
        }

        return databaseInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}
