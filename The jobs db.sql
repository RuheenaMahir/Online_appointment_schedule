create database the_jobs;

use the_jobs;

create table Consultant (
Consultant_id varchar(20) primary key not null,
Fname varchar(30),
Lname varchar(30),
Email varchar(50),
Phone varchar(10),
Specialized_country varchar(20),
Specialized_job varchar(50),
Available_date date,
Available_time time
);

create table Jobseeker (
Jobseeker_id varchar(20) primary key not null,
Fname varchar(50),
Lname varchar(50),
Email varchar(50),
Phone varchar(10),
Job_type varchar(50)
);

create table Appointment (
Appointment_id varchar(10) primary key not null,
Consultant_id varchar(20),
Jobseeker_id varchar(20),
A_date date,
A_time time,
A_status varchar(20),
foreign key (Consultant_id) references Consultant(Consultant_id),
foreign key (Jobseeker_id) references Jobseeker(Jobseeker_id)
);



