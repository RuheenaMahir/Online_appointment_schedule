package com.example.appointment.dao;

import com.example.appointment.models.JobSeeker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobSeekerDAO {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/the_jobs";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public List<JobSeeker> getAllJobSeekers() throws ClassNotFoundException {
        List<JobSeeker> jobSeekers = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Jobseeker")) {

            while (resultSet.next()) {
                String jobSeekerId = resultSet.getString("Jobseeker_id");
                String firstName = resultSet.getString("Fname");
                String lastName = resultSet.getString("Lname");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                String jobType = resultSet.getString("Job_type");

                // Create a new JobSeeker object and add it to the jobSeekers list
                JobSeeker jobSeeker = new JobSeeker(jobSeekerId, firstName, lastName, username, password, email, phone, jobType);
                jobSeekers.add(jobSeeker);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobSeekers;
    }

    public JobSeeker getJobSeekerById(String jobSeekerId) throws ClassNotFoundException {
        JobSeeker jobSeeker = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Jobseeker WHERE Jobseeker_id = ?")) {

            preparedStatement.setString(1, jobSeekerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("Fname");
                String lastName = resultSet.getString("Lname");
                String username = resultSet.getString("Username");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                String jobType = resultSet.getString("Job_type");
                String password = resultSet.getString("Password");

                // Create a new JobSeeker object
                jobSeeker = new JobSeeker(jobSeekerId, firstName, lastName, username, password, email, phone, jobType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobSeeker;
    }


    public void createJobSeeker(JobSeeker jobSeeker) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Jobseeker (Jobseeker_id, Fname, Lname, Username, Password, Email, Phone, Job_type) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, jobSeeker.getJobSeekerId());
            preparedStatement.setString(2, jobSeeker.getFirstName());
            preparedStatement.setString(3, jobSeeker.getLastName());
            preparedStatement.setString(4, jobSeeker.getUsername());
            preparedStatement.setString(5, jobSeeker.getPassword());
            preparedStatement.setString(6, jobSeeker.getEmail());
            preparedStatement.setString(7, jobSeeker.getPhone());
            preparedStatement.setString(8, jobSeeker.getJobType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }


    public void updateJobSeeker(JobSeeker jobSeeker) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Jobseeker SET Fname = ?, Lname = ?, Username = ?, Password = ?, Email = ?, " +
                             "Phone = ?, Job_type = ? WHERE Jobseeker_id = ?")) {

            preparedStatement.setString(1, jobSeeker.getFirstName());
            preparedStatement.setString(2, jobSeeker.getLastName());
            preparedStatement.setString(3, jobSeeker.getUsername());
            preparedStatement.setString(4, jobSeeker.getPassword());
            preparedStatement.setString(5, jobSeeker.getEmail());
            preparedStatement.setString(6, jobSeeker.getPhone());
            preparedStatement.setString(7, jobSeeker.getJobType());
            preparedStatement.setString(8, jobSeeker.getJobSeekerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteJobSeeker(String jobSeekerId) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Jobseeker WHERE Jobseeker_id = ?")) {

            preparedStatement.setString(1, jobSeekerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public JobSeeker getJobSeekerByUsername(String username) throws ClassNotFoundException {
        JobSeeker jobSeeker = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Jobseeker WHERE Username = ?")) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String jobSeekerId = resultSet.getString("Jobseeker_id");
                String firstName = resultSet.getString("Fname");
                String lastName = resultSet.getString("Lname");
                String retrievedUsername = resultSet.getString("Username");
                String password = resultSet.getString("Password");  // In a real app, hash and salt the password
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                String jobType = resultSet.getString("Job_type");

                // Create a new JobSeeker object
                jobSeeker = new JobSeeker(jobSeekerId, firstName, lastName, retrievedUsername, password, email, phone, jobType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobSeeker;
    }

}
