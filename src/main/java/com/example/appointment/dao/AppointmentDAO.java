package com.example.appointment.dao;

import com.example.appointment.models.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/the_jobs";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public List<Appointment> getAllAppointments() throws ClassNotFoundException {
        List<Appointment> appointments = new ArrayList<>();

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Appointment")) {

            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("Appointment_id");
                LocalDateTime appointmentDateTime = resultSet.getTimestamp("A_date").toLocalDateTime();
                int consultantId = resultSet.getInt("Consultant_id");
                int jobSeekerId = resultSet.getInt("Jobseeker_id");
                String appointmentDetails = resultSet.getString("A_status");

                // Create an Appointment object and add it to the appointments list
                Appointment appointment = new Appointment(appointmentId, appointmentDateTime, consultantId, jobSeekerId, appointmentDetails);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public Appointment getAppointmentById(String appointmentId) throws ClassNotFoundException {
        Appointment appointment = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Appointment WHERE Appointment_id = ?")) {

            preparedStatement.setString(1, appointmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedAppointmentId = resultSet.getInt("Appointment_id");
                    LocalDateTime appointmentDateTime = LocalDateTime.of(
                            resultSet.getDate("A_date").toLocalDate(),
                            resultSet.getTime("A_time").toLocalTime());
                    int consultantId = resultSet.getInt("Consultant_id");
                    int jobSeekerId = resultSet.getInt("Jobseeker_id");
                    String appointmentDetails = resultSet.getString("A_status");

                    appointment = new Appointment(
                            retrievedAppointmentId,
                            appointmentDateTime,
                            consultantId,
                            jobSeekerId,
                            appointmentDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    public void createAppointment(Appointment appointment) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Appointment (Appointment_id, Consultant_id, Jobseeker_id, A_date, A_time, A_status) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            // Set values for the prepared statement
            preparedStatement.setInt(1, appointment.getAppointmentId());
            preparedStatement.setInt(2, appointment.getConsultantId());
            preparedStatement.setInt(3, appointment.getJobSeekerId());
            preparedStatement.setDate(4, Date.valueOf(appointment.getAppointmentDateTime().toLocalDate()));
            preparedStatement.setTime(5, Time.valueOf(appointment.getAppointmentDateTime().toLocalTime()));
            preparedStatement.setString(6, appointment.getAppointmentDetails());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAppointment(Appointment appointment) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Appointment SET Consultant_id = ?, Jobseeker_id = ?, A_date = ?, A_time = ?, A_status = ? " +
                             "WHERE Appointment_id = ?")) {

            System.out.println("Connection succedded");

            // Set values for the prepared statement
            preparedStatement.setInt(1, appointment.getConsultantId());
            preparedStatement.setInt(2, appointment.getJobSeekerId());
            preparedStatement.setDate(3, Date.valueOf(appointment.getAppointmentDateTime().toLocalDate()));
            preparedStatement.setTime(4, Time.valueOf(appointment.getAppointmentDateTime().toLocalTime()));
            preparedStatement.setString(5, appointment.getAppointmentDetails());
            preparedStatement.setInt(6, appointment.getAppointmentId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAppointmentsByUsername(String username) throws ClassNotFoundException {
        List<Appointment> appointments = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Appointment WHERE Consultant_id = ? OR Jobseeker_id = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("Appointment_id");
                LocalDateTime appointmentDateTime = resultSet.getTimestamp("A_date").toLocalDateTime();
                int consultantId = resultSet.getInt("Consultant_id");
                int jobSeekerId = resultSet.getInt("Jobseeker_id");
                String appointmentDetails = resultSet.getString("A_status");

                // Create an Appointment object and add it to the appointments list
                Appointment appointment = new Appointment(appointmentId, appointmentDateTime, consultantId, jobSeekerId, appointmentDetails);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }

    public void deleteAppointmentById(int appointmentId) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Appointment WHERE Appointment_id = ?")) {

            preparedStatement.setInt(1, appointmentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
