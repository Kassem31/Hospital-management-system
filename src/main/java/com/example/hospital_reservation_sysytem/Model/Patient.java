package com.example.hospital_reservation_sysytem.Model;

import java.security.PublicKey;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Patient {
    public static int CURR_PATIENT_ID;
    public static String CURR_PATIENT_NAME;
    public static Time CURR_PATIENT_SELECTED_TIME;
    public static Date CURR_PATIENT_SELECTED_DATE;
    private static int ID_counter;
    String name;
    public int id;
    String password;
    String email;
    int age;
    boolean male;
    String phoneNumber;
    String address;

    //Create new patient
   public Patient(String name,String password,String email,int age,boolean male,String phoneNumber,String address) throws SQLException {
        this.id=ID_counter++;
        this.name=name;
        this.password=password;
        this.email=email;
        this.age=age;
        this.male=male;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }

    //Get from Database with id
    public  Patient(String name,String password,String email,int age,boolean male,String phoneNumber,String address,int id){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.age=age;
        this.male=male;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int PatientId) {
        this.id = PatientId;
    }

    public int getId() {
        return id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
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


    public void PrintPatient(){
        System.out.println("Patient name: "+this.name+" Patient email: "+this.email+" Patient age: "+this.age+" Patient gender: "+this.male+" Patient phone number: "+this.phoneNumber+" address: "+this.address+" id:  "+this.id);

    }


}
