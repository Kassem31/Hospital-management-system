package com.example.hospital_reservation_sysytem.Model;

import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.Time;

public class PatientAppointments {
    public Date patientDate;
    public Time patientTime;
    public String doctorName;
    public String doctorDepartment;


    public PatientAppointments(Date patientDate, Time patientTime,String doctorDepartment, String doctorName) {
        this.patientDate = patientDate;
        this.patientTime = patientTime;
        this.doctorName = doctorName;
        this.doctorDepartment = doctorDepartment;

    }

    public Date getDate() {
        return patientDate;
    }

    public void setDate(Date date) {
        this.patientDate = date;
    }

    public Time getTime() {
        return patientTime;
    }

    public void setTime(Time time) {
        this.patientTime = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {this.doctorName = doctorName;}


    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(String doctorDepartment) {this.doctorDepartment = doctorDepartment;}

    @Override
    public String toString() {
        return "PatientAppointments{" +
                "date=" + patientDate +
                ", time=" + patientTime +
                ", doctorDepartment='" + doctorDepartment + '\'' +
                ", doctorName='" + doctorName + '\'' +
                '}';
    }
}
