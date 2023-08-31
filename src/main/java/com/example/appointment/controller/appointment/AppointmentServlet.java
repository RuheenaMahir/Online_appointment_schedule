package com.example.appointment.controller.appointment;

import com.example.appointment.dao.AppointmentDAO;
import com.example.appointment.models.Appointment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all appointments
        String appointmentIdParameter = request.getParameter("appointmentId");

        if (appointmentIdParameter != null) {
            // Retrieve an appointment by its ID
            int appointmentId = Integer.parseInt(appointmentIdParameter);
            Appointment appointment = null;
            try {
                appointment = appointmentDAO.getAppointmentById(String.valueOf(appointmentId));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (appointment != null) {
                // Convert the appointment to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());

                String jsonAppointment = objectMapper.writeValueAsString(appointment);

                // Set content type and write JSON response
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonAppointment);
            } else {
            }
        } else {
            // Retrieve all appointments
            List<Appointment> appointments = null;
            try {
                appointments = appointmentDAO.getAllAppointments();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // Convert the list of appointments to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String jsonAppointments = objectMapper.writeValueAsString(appointments);

            // Set content type and write JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonAppointments);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createAppointment(request, response);
                break;
            case "update":
                updateAppointment(request, response);
                break;
            case "delete":
                deleteAppointment(request, response);
                break;
            default:
                // Handle other cases or show an error message
                break;
        }
    }

    private void createAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve form data
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int consultantId = Integer.parseInt(request.getParameter("consultantId"));
        int jobSeekerId = Integer.parseInt(request.getParameter("jobSeekerId"));
        LocalDateTime appointmentDateTime = LocalDateTime.parse(request.getParameter("appointmentDateTime"));
        String appointmentDetails = request.getParameter("appointmentDetails");

        // Create an Appointment object
        Appointment appointment = new Appointment(appointmentId, appointmentDateTime, consultantId, jobSeekerId, appointmentDetails);

        // Create the appointment
        try {
            appointmentDAO.createAppointment(appointment);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Redirect to the appointment listing page or show a success message
        response.sendRedirect(request.getContextPath() + "/appointment");
    }

    private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve form data
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int consultantId = Integer.parseInt(request.getParameter("consultantId"));
        int jobSeekerId = Integer.parseInt(request.getParameter("jobSeekerId"));
        LocalDateTime appointmentDateTime = LocalDateTime.parse(request.getParameter("appointmentDateTime"));
        String appointmentDetails = request.getParameter("appointmentDetails");

        // Create an updated Appointment object
        Appointment updatedAppointment = new Appointment(appointmentId, appointmentDateTime, consultantId, jobSeekerId, appointmentDetails);

        // Update the appointment
        try {
            appointmentDAO.updateAppointment(updatedAppointment);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Redirect to a confirmation page or show a success message
        response.sendRedirect(request.getContextPath() + "/appointment");
    }

    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

        try {
            appointmentDAO.deleteAppointmentById(appointmentId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the exception, show an error page, etc.
        }
        // Redirect to a confirmation page or show a success message
        response.sendRedirect(request.getContextPath() + "/appointment");
    }




}
