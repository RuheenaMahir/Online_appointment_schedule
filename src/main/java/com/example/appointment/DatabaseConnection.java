package com.example.appointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        try {
            // Replace "your-database-url", "your-username", and "your-password" with your actual database connection details
            String url = "jdbc:mysql://localhost:3306/the_jobs";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to the database!\n\n\n");

        } catch (SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
