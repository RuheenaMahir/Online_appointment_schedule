package com.appointmentscheduling;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<User> users;

    public AuthenticationService() {
        // Initialize the list of users
        users = new ArrayList<>();
    }

    // Method to register a new user (consultant or job seeker)
    public void registerUser(User user) {
        users.add(user);
    }

    // Method to perform user login
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // User not found
    }
}
