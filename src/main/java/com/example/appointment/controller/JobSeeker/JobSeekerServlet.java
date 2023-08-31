package com.example.appointment.controller.JobSeeker;

import com.example.appointment.dao.JobSeekerDAO;
import com.example.appointment.models.JobSeeker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/jobseeker")
public class JobSeekerServlet extends HttpServlet {
    private JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobSeekerIdParam = request.getParameter("id");

        if (jobSeekerIdParam != null) {
            int jobSeekerId = Integer.parseInt(jobSeekerIdParam);
            JobSeeker jobSeeker = null;

            try {
                jobSeeker = jobSeekerDAO.getJobSeekerById(String.valueOf(jobSeekerId));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (jobSeeker != null) {
                objectMapper.registerModule(new JavaTimeModule());

                // Serialize the job seeker object to JSON
                String jobSeekerJson = objectMapper.writeValueAsString(jobSeeker);

                response.setContentType("application/json");
                response.getWriter().write(jobSeekerJson);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Job Seeker not found");
            }
        } else {
            List<JobSeeker> jobSeekers = null;

            try {
                jobSeekers = jobSeekerDAO.getAllJobSeekers();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            objectMapper.registerModule(new JavaTimeModule());

            // Serialize the job seekers list to JSON
            String jobSeekersJson = objectMapper.writeValueAsString(jobSeekers);

            response.setContentType("application/json");
            response.getWriter().write(jobSeekersJson);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createJobSeeker(request, response);
                    break;
                case "update":
                    updateJobSeeker(request, response);
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
                        deleteJobSeeker(request, response);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                // Add more cases as needed
            }
        }
    }

    private void createJobSeeker(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jobSeekerId = request.getParameter("jobSeekerId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String jobType = request.getParameter("jobType");

        JobSeeker jobSeeker = new JobSeeker(jobSeekerId, firstName, lastName, username,
                password, email, phone, jobType);

        jobSeekerDAO.createJobSeeker(jobSeeker);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write("Job Seeker created successfully");
    }


    private void updateJobSeeker(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int jobSeekerId = Integer.parseInt(request.getParameter("jobSeekerId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String jobType = request.getParameter("jobType");
        // Retrieve other form data as needed

        JobSeeker jobSeeker = new JobSeeker(String.valueOf(jobSeekerId), firstName, lastName, username,
                password, email, phone, jobType);

        jobSeekerDAO.updateJobSeeker(jobSeeker);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Job Seeker updated successfully");
    }


    private void deleteJobSeeker(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String jobSeekerId = request.getParameter("jobSeekerId");

        if (jobSeekerId != null) {
            jobSeekerDAO.deleteJobSeeker(jobSeekerId);
        }

        response.getWriter().write("Job Seeker deleted successfully");
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            JobSeeker jobSeeker = jobSeekerDAO.getJobSeekerByUsername(username);

            if (jobSeeker != null) {
                String storedPassword = jobSeeker.getPassword(); // This is the stored password hash

                // You should compare the hashed password, not plaintext passwords
                // In a real application, use a proper password hashing library like BCrypt
                if (storedPassword.equals(password)) {
                    response.getWriter().write("Password matches");
                } else {
                    response.getWriter().write("Password does not match");
                }
            } else {
                response.getWriter().write("User not found");
            }
        } else {
            response.getWriter().write("Username and password must be provided");
        }
    }

}


