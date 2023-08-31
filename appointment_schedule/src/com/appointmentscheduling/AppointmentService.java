package com.appointmentscheduling;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Appointment> appointments;

    public AppointmentService() {
        // Initialize the list of appointments
        appointments = new ArrayList<>();
    }

    // Method to book a new appointment
    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Method to check consultant availability for a given date
    public boolean isConsultantAvailable(String consultantUsername, String date) {
        for (Appointment appointment : appointments) {
            if (appointment.getConsultant().getUsername().equals(consultantUsername) &&
                    appointment.getDate().equals(date)) {
                return false; // Consultant is already booked for the given date
            }
        }
        return true; // Consultant is available for the given date
    }

    // Method to get appointment history for a user (consultant or job seeker)
    public List<Appointment> getAppointmentHistory(String username) {
        List<Appointment> appointmentHistory = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getConsultant().getUsername().equals(username) ||
                    appointment.getJobSeeker().getUsername().equals(username)) {
                appointmentHistory.add(appointment);
            }
        }
        return appointmentHistory;
    }

    // Method to get a list of all appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }
}
