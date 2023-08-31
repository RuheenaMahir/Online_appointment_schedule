package com.appointmentscheduling;

import java.util.ArrayList;
import java.util.List;

public class Consultant extends User {
    private String specialization;
    private List<String> availableDates;
    private List<Appointment> appointmentHistory;

    // Constructor
    public Consultant(String username, String password, String email, String phoneNumber, String specialization) {
        super(username, password, email, phoneNumber, "consultant");
        this.specialization = specialization;
        this.availableDates = new ArrayList<>();
        this.appointmentHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(List<String> availableDates) {
        this.availableDates = availableDates;
    }

    public List<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    public void setAppointmentHistory(List<Appointment> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }

    // Add a single available date
    public void addAvailableDate(String date) {
        this.availableDates.add(date);
    }

    // Add an appointment to the consultant's history
    public void addAppointmentToHistory(Appointment appointment) {
        this.appointmentHistory.add(appointment);
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "Consultant [username=" + getUsername() + ", password=" + getPassword() + ", email=" + getEmail()
                + ", phoneNumber=" + getPhoneNumber() + ", specialization=" + specialization + "]";
    }
}
