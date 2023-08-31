package com.example.appointment.controller.consultant;

import com.example.appointment.dao.ConsultantDAO;
import com.example.appointment.models.Consultant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/consultant")
public class ConsultantServlet extends HttpServlet {
    private ConsultantDAO consultantDAO = new ConsultantDAO();
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consultantIdParam = request.getParameter("id");

        if (consultantIdParam != null) {
            int consultantId = Integer.parseInt(consultantIdParam);
            Consultant consultant = null;

            try {
                consultant = consultantDAO.getConsultantById(String.valueOf(consultantId));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (consultant != null) {
                objectMapper.registerModule(new JavaTimeModule());

                // Serialize the consultant object to JSON
                String consultantJson = objectMapper.writeValueAsString(consultant);

                response.setContentType("application/json");
                response.getWriter().write(consultantJson);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Consultant not found");
            }
        } else {
            List<Consultant> consultants = null;

            try {
                consultants = consultantDAO.getAllConsultants();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            objectMapper.registerModule(new JavaTimeModule());

            // Serialize the consultants list to JSON
            String consultantsJson = objectMapper.writeValueAsString(consultants);

            response.setContentType("application/json");
            response.getWriter().write(consultantsJson);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    try {
                        createConsultant(request, response);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "update":
                    try {
                        updateConsultant(request, response);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "auth":
                    try {
                        authenticate(request, response);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "delete":
                    try {
                        deleteConsultant(request, response);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                // Add more cases as needed
            }
        }
    }

    private void createConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String consultantId = request.getParameter("consultantId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String specializedCountry = request.getParameter("specializedCountry");
        String specializedJob = request.getParameter("specializedJob");
        LocalDate availableDate = LocalDate.parse(request.getParameter("availableDate"));
        LocalTime availableTime = LocalTime.parse(request.getParameter("availableTime"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Consultant consultant = new Consultant(consultantId, firstName, lastName, email, phone,
                specializedCountry, specializedJob, availableDate, availableTime, username, password);

        consultantDAO.createConsultant(consultant);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("Consultant created successfully");
    }


    private void updateConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        int consultantId = Integer.parseInt(request.getParameter("consultantId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String specializedCountry = request.getParameter("specializedCountry");
        String specializedJob = request.getParameter("specializedJob");
        LocalDate availableDate = LocalDate.parse(request.getParameter("availableDate"));
        LocalTime availableTime = LocalTime.parse(request.getParameter("availableTime"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Consultant consultant = new Consultant(String.valueOf(consultantId), firstName, lastName, email, phone,
                specializedCountry, specializedJob, availableDate, availableTime, username, password);


        consultantDAO.updateConsultant(consultant);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Consultant updated successfully");
    }


    private void deleteConsultant(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String consultantId = request.getParameter("consultantId");

        if (consultantId != null) {
            consultantDAO.deleteConsultant(consultantId);
        }

        response.sendRedirect(request.getContextPath() + "/consultant");
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            Consultant consultant = consultantDAO.getConsultantByUsername(username);

            if (consultant != null) {
                String storedPassword = consultant.getPassword(); // This is the stored password hash

                // You should compare the hashed password, not plaintext passwords
                // In a real application, use a proper password hashing library like BCrypt
                if (storedPassword.equals(password)) {
                    response.getWriter().write("Password matches");
                } else {
                    response.getWriter().write("Password does not match");
                }
            } else {
                response.getWriter().write("Consultant not found");
            }
        } else {
            response.getWriter().write("Username and password must be provided");
        }
    }
}
