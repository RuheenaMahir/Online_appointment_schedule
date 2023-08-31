package com.example.appointment.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consultant {
    private String consultantId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String specializedCountry;
    private String specializedJob;
    private LocalDate availableDate;
    private LocalTime availableTime;
    private String username;
    private String password;

    public Consultant(String consultantId, String firstName, String lastName, String email, String phone,
                      String specializedCountry, String specializedJob, LocalDate availableDate,
                      LocalTime availableTime, String username, String password) {
        this.consultantId = consultantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specializedCountry = specializedCountry;
        this.specializedJob = specializedJob;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecializedCountry() {
        return specializedCountry;
    }

    public void setSpecializedCountry(String specializedCountry) {
        this.specializedCountry = specializedCountry;
    }

    public String getSpecializedJob() {
        return specializedJob;
    }

    public void setSpecializedJob(String specializedJob) {
        this.specializedJob = specializedJob;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public LocalTime getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(LocalTime availableTime) {
        this.availableTime = availableTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
