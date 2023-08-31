package com.example.appointment.dao;

import com.example.appointment.models.Consultant;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultantDAO {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/the_jobs";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public List<Consultant> getAllConsultants() throws ClassNotFoundException {
        List<Consultant> consultants = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Consultant")) {

            while (resultSet.next()) {
                String consultantId = resultSet.getString("Consultant_id");
                String firstName = resultSet.getString("Fname");
                String lastName = resultSet.getString("Lname");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                String specializedCountry = resultSet.getString("Specialized_country");
                String specializedJob = resultSet.getString("Specialized_job");
                LocalDate availableDate = resultSet.getDate("Available_date").toLocalDate();
                LocalTime availableTime = resultSet.getTime("Available_time").toLocalTime();
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                // Create a Consultant object and add it to the consultants list
                Consultant consultant = new Consultant(consultantId, firstName, lastName, email, phone,
                        specializedCountry, specializedJob, availableDate, availableTime, username, password);
                consultants.add(consultant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultants;
    }

    public Consultant getConsultantById(String consultantId) throws ClassNotFoundException {
        Consultant consultant = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Consultant WHERE Consultant_id = ?")) {

            preparedStatement.setInt(1, Integer.parseInt(consultantId));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    consultant = mapResultSetToConsultant(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultant;
    }

    private Consultant mapResultSetToConsultant(ResultSet resultSet) throws SQLException {
        int consultantId = resultSet.getInt("Consultant_id");
        String fname = resultSet.getString("Fname");
        String lname = resultSet.getString("Lname");
        String email = resultSet.getString("Email");
        String phone = resultSet.getString("Phone");
        String specializedCountry = resultSet.getString("Specialized_country");
        String specializedJob = resultSet.getString("Specialized_job");
        LocalDate availableDate = resultSet.getDate("Available_date").toLocalDate();
        LocalTime availableTime = resultSet.getTime("Available_time").toLocalTime();
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        // Create and return a Consultant object
        return new Consultant(String.valueOf(consultantId), fname, lname, email, phone, specializedCountry,
                specializedJob, availableDate, availableTime, username, password);
    }


    public void createConsultant(Consultant consultant) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Consultant (Consultant_id, Fname, Lname, Email, Phone, Specialized_country, " +
                             "Specialized_job, Available_date, Available_time, username, password) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Set values for the prepared statement
            preparedStatement.setString(1, consultant.getConsultantId());
            preparedStatement.setString(2, consultant.getFirstName());
            preparedStatement.setString(3, consultant.getLastName());
            preparedStatement.setString(4, consultant.getEmail());
            preparedStatement.setString(5, consultant.getPhone());
            preparedStatement.setString(6, consultant.getSpecializedCountry());
            preparedStatement.setString(7, consultant.getSpecializedJob());
            preparedStatement.setDate(8, Date.valueOf(consultant.getAvailableDate()));
            preparedStatement.setTime(9, Time.valueOf(consultant.getAvailableTime()));
            preparedStatement.setString(10, consultant.getUsername());
            preparedStatement.setString(11, consultant.getPassword()); // In a real app, hash and salt the password

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateConsultant(Consultant consultant) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Consultant SET Fname = ?, Lname = ?, Email = ?, Phone = ?, Specialized_country = ?, " +
                             "Specialized_job = ?, Available_date = ?, Available_time = ?, username = ?, password = ? " +
                             "WHERE Consultant_id = ?")) {

            // Set values for the prepared statement
            preparedStatement.setString(1, consultant.getFirstName());
            preparedStatement.setString(2, consultant.getLastName());
            preparedStatement.setString(3, consultant.getEmail());
            preparedStatement.setString(4, consultant.getPhone());
            preparedStatement.setString(5, consultant.getSpecializedCountry());
            preparedStatement.setString(6, consultant.getSpecializedJob());
            preparedStatement.setDate(7, Date.valueOf(consultant.getAvailableDate()));
            preparedStatement.setTime(8, Time.valueOf(consultant.getAvailableTime()));
            preparedStatement.setString(9, consultant.getUsername());
            preparedStatement.setString(10, consultant.getPassword()); // In a real app, hash and salt the password
            preparedStatement.setString(11, consultant.getConsultantId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteConsultant(String consultantId) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Consultant WHERE Consultant_id = ?")) {

            // Set the consultant ID in the prepared statement
            preparedStatement.setString(1, consultantId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Consultant getConsultantByUsername(String username) throws ClassNotFoundException {
        Consultant consultant = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Consultant WHERE username = ?")) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int consultantId = resultSet.getInt("Consultant_id");
                String firstName = resultSet.getString("Fname");
                String lastName = resultSet.getString("Lname");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                String specializedCountry = resultSet.getString("Specialized_country");
                String specializedJob = resultSet.getString("Specialized_job");
                LocalDate availableDate = resultSet.getDate("Available_date").toLocalDate();
                LocalTime availableTime = resultSet.getTime("Available_time").toLocalTime();
                String password = resultSet.getString("password");

                // Create a new Consultant object
                consultant = new Consultant(String.valueOf(consultantId), firstName, lastName, email, phone,
                        specializedCountry, specializedJob, availableDate, availableTime, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultant;
    }

}
