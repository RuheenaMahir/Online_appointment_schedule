package com.example.appointment.models;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime appointmentDateTime;
    private int consultantId;
    private int jobSeekerId;
    private String appointmentDetails;
    private Consultant consultant;
    private JobSeeker jobSeeker;
    private String date;

    // Constructor
    public Appointment(int appointmentId, LocalDateTime appointmentDateTime, int consultantId,
            int jobSeekerId, String appointmentDetails) {
        this.appointmentId = appointmentId;
        this.appointmentDateTime = appointmentDateTime;
        this.consultantId = consultantId;
        this.jobSeekerId = jobSeekerId;
        this.appointmentDetails = appointmentDetails;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId= consultantId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }

    public void setAppointmentDetails(String appointmentDetails) {
        this.appointmentDetails = appointmentDetails;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public String getDate() {
        return date;
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", appointmentDateTime=" + appointmentDateTime
                + ", consultantUsername=" + consultantId + ", jobSeekerUsername=" + jobSeekerId
                + ", appointmentDetails=" + appointmentDetails + "]";
    }
}
