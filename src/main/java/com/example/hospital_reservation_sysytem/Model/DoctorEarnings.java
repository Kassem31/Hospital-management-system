package com.example.hospital_reservation_sysytem.Model;

import java.sql.Date;

public class DoctorEarnings {
    Date date;
    float earnings;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DoctorEarnings{" +
                "date=" + date +
                ", earnings=" + earnings +
                '}';
    }

    public float getEarnings() {
        return earnings;
    }

    public void setEarnings(float earnings) {
        this.earnings = earnings;
    }

    public DoctorEarnings(Date date, float earnings) {
        this.date = date;
        this.earnings = earnings;
    }
}
