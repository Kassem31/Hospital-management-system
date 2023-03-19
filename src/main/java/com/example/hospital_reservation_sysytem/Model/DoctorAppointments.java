package com.example.hospital_reservation_sysytem.Model;

import java.sql.Date;
import java.sql.Time;

public class DoctorAppointments {
    public Date date;
    public  Time time;
    public String patientName;
    public boolean gender;
    public String phoneNumber;
    public String address;

    public DoctorAppointments(Date date, Time time, String patientName, boolean gender, String phoneNumber, String address) {
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public boolean isGender() {return gender;}

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DoctorAppointments{" +
                "date=" + date +
                ", time=" + time +
                ", patientName='" + patientName + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
