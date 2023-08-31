package com.appointmentscheduling;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime appointmentDateTime;
    private String consultantUsername;
    private String jobSeekerUsername;
    private String appointmentDetails;
    private Consultant consultant;
    private JobSeeker jobSeeker;
    private String date;

    // Constructor
    public Appointment(int appointmentId, LocalDateTime appointmentDateTime, String consultantUsername,
            String jobSeekerUsername, String appointmentDetails) {
        this.appointmentId = appointmentId;
        this.appointmentDateTime = appointmentDateTime;
        this.consultantUsername = consultantUsername;
        this.jobSeekerUsername = jobSeekerUsername;
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

    public String getConsultantUsername() {
        return consultantUsername;
    }

    public void setConsultantUsername(String consultantUsername) {
        this.consultantUsername = consultantUsername;
    }

    public String getJobSeekerUsername() {
        return jobSeekerUsername;
    }

    public void setJobSeekerUsername(String jobSeekerUsername) {
        this.jobSeekerUsername = jobSeekerUsername;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }

    public void setAppointmentDetails(String appointmentDetails) {
        this.appointmentDetails = appointmentDetails;
    }
    public Appointment(Consultant consultant, JobSeeker jobSeeker, String date) {
        this.consultant = consultant;
        this.jobSeeker = jobSeeker;
        this.date = date;
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
                + ", consultantUsername=" + consultantUsername + ", jobSeekerUsername=" + jobSeekerUsername
                + ", appointmentDetails=" + appointmentDetails + "]";
    }
}
