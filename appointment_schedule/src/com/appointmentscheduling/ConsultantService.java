package com.appointmentscheduling;

import java.util.ArrayList;
import java.util.List;

public class ConsultantService {
    private List<Consultant> consultants;

    public ConsultantService() {
        // Initialize the list of consultants
        consultants = new ArrayList<>();
    }

    // Method to add a new consultant
    public void addConsultant(Consultant consultant) {
        consultants.add(consultant);
    }

    // Method to update the availability of a consultant
    public void updateConsultantAvailability(String username, List<String> availableDates) {
        for (Consultant consultant : consultants) {
            if (consultant.getUsername().equals(username)) {
                consultant.setAvailableDates(availableDates);
                return;
            }
        }
    }

    // Method to get a list of all consultants
    public List<Consultant> getAllConsultants() {
        return consultants;
    }

    // Method to get a consultant by username
    public Consultant getConsultantByUsername(String username) {
        for (Consultant consultant : consultants) {
            if (consultant.getUsername().equals(username)) {
                return consultant;
            }
        }
        return null; // Consultant not found
    }
}
