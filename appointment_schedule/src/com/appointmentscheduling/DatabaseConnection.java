package com.appointmentscheduling;

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
            String url = "jdbc:mysql://your-database-url";
            String username = "your-username";
            String password = "your-password";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
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

    // Example method to insert a new consultant into the database
    public void insertConsultant(Consultant consultant) {
        String query = "INSERT INTO consultants (username, password, email, phone_number, specialization) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, consultant.getUsername());
            preparedStatement.setString(2, consultant.getPassword());
            preparedStatement.setString(3, consultant.getEmail());
            preparedStatement.setString(4, consultant.getPhoneNumber());
            preparedStatement.setString(5, consultant.getSpecialization());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Example method to retrieve all consultants from the database
    public List<Consultant> getAllConsultants() {
        List<Consultant> consultants = new ArrayList<>();

        String query = "SELECT * FROM consultants";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String specialization = resultSet.getString("specialization");

                Consultant consultant = new Consultant(username, password, email, phoneNumber, specialization);
                consultants.add(consultant);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultants;
    }

    // Add more methods for other database operations as needed

    // Other methods for executing queries, updates, etc.
}
