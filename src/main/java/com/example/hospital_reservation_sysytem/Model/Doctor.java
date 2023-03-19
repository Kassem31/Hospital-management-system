package com.example.hospital_reservation_sysytem.Model;

public class Doctor {
    public static int CURR_DOCTOR_ID;
    public static String CURR_DOCTOR_NAME;
    public static float CURR_DOCTOR_SALARY;
    private static int ID_counter = 1;
    String name;
    public final int id;
    String password;
    String email;
    String department;
    float salary;

    //Create new Doctor
    public Doctor(String name,String password,String email,String department,float salary){
        this.id=ID_counter++;
        this.name=name;
        this.password=password;
        this.email=email;
        this.department=department;
        this.salary=salary;
    }
    //Get doctor from database
    public Doctor(String name,String password,String email,String department,float salary,int id){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
        this.department=department;
        this.salary=salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void PrintDoctor(){
        System.out.println("Doctor name: "+this.name+" Doctor email: "+this.email+" Doctor department: "+this.department+" Doctor salary: "+this.salary+" Doctor id: "+this.id);

    }
}
