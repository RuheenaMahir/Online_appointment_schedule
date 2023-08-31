package com.appointmentscheduling;

import java.util.ArrayList;
import java.util.List;

public class JobSeeker extends User {
    private String preferredJobType;
    private List<Appointment> appointmentHistory;

    // Constructor
    public JobSeeker(String username, String password, String email, String phoneNumber, String preferredJobType) {
        super(username, password, email, phoneNumber, "job_seeker");
        this.preferredJobType = preferredJobType;
        this.appointmentHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getPreferredJobType() {
        return preferredJobType;
    }

    public void setPreferredJobType(String preferredJobType) {
        this.preferredJobType = preferredJobType;
    }

    public List<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    public void setAppointmentHistory(List<Appointment> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }

    // Add an appointment to the job seeker's history
    public void addAppointmentToHistory(Appointment appointment) {
        this.appointmentHistory.add(appointment);
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "JobSeeker [username=" + getUsername() + ", password=" + getPassword() + ", email=" + getEmail()
                + ", phoneNumber=" + getPhoneNumber() + ", preferredJobType=" + preferredJobType + "]";
    }
}
