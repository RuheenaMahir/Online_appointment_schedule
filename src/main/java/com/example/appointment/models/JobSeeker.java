package com.example.appointment.models;

import java.io.Serializable;

public class JobSeeker implements Serializable {
    private String jobSeekerId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String jobType;

    public JobSeeker(String jobSeekerId, String firstName, String lastName, String username,
                     String password, String email, String phone, String jobType) {
        this.jobSeekerId = jobSeekerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.jobType = jobType;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
