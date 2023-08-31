package com.appointmentscheduling;

import java.util.ArrayList;
import java.util.List;

public class JobSeekerService {
    private List<JobSeeker> jobSeekers;

    public JobSeekerService() {
        // Initialize the list of job seekers
        jobSeekers = new ArrayList<>();
    }

    // Method to add a new job seeker
    public void addJobSeeker(JobSeeker jobSeeker) {
        jobSeekers.add(jobSeeker);
    }

    // Method to update the preferred job type of a job seeker
    public void updatePreferredJobType(String username, String preferredJobType) {
        for (JobSeeker jobSeeker : jobSeekers) {
            if (jobSeeker.getUsername().equals(username)) {
                jobSeeker.setPreferredJobType(preferredJobType);
                return;
            }
        }
    }

    // Method to get a list of all job seekers
    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekers;
    }

    // Method to get a job seeker by username
    public JobSeeker getJobSeekerByUsername(String username) {
        for (JobSeeker jobSeeker : jobSeekers) {
            if (jobSeeker.getUsername().equals(username)) {
                return jobSeeker;
            }
        }
        return null; // Job seeker not found
    }
}
