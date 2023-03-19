package com.example.hospital_reservation_sysytem.Model;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    int patientID, doctorId;
    Date date;
    Time time;

    public Appointment(int patientID,int DoctorId,Date date,Time time){
        this.patientID=patientID;
        this.doctorId=DoctorId;
        this.date=date;
        this.time=time;
    }


    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public void printAppointment(){
        System.out.println("PatientId: "+this.patientID+" doctor id: "+this.doctorId+" date: "+this.date+" time: "+this.time);
    }

}
