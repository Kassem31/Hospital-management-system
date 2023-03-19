package com.example.hospital_reservation_sysytem;

import com.example.hospital_reservation_sysytem.Model.Database;
import com.example.hospital_reservation_sysytem.Model.Doctor;
import com.example.hospital_reservation_sysytem.Model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {

    public Button loginBtn;
    public TextField passwordID;
    public TextField emailID;
    public Button registerBackBtn; //the back button in register screen
    public Button registerBtn; //for register now button in login screen
    public Button registeredBtn; //after filling data in register screen
    public TextField emailIDInRegister;
    public PasswordField passwordIDInRegister;
    public TextField patientNameInRegister;
    public ChoiceBox genderIDInRegister;
    public Slider ageIDInRegister;
    public TextField phoneIDInRegister;
    public TextField adressIDInRegister;
    public Label checkCredentials;
    public Button loginBtnDoctor;
    public Label IncorrectLabelID;
    public Button loginBtnPatient;

    public void registerClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register-page.fxml"));

        Stage window = (Stage) registerBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    public void registerBackClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-page.fxml"));

        Stage window = (Stage) registerBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    public void registeredClicked(ActionEvent actionEvent) throws SQLException, IOException {
    String email=emailIDInRegister.getText();
    String password=passwordIDInRegister.getText();
    String name=patientNameInRegister.getText();
    String type=genderIDInRegister.getValue().toString();
    int age=(int)ageIDInRegister.getValue();
    String phoneNumber=phoneIDInRegister.getText();
    String address=adressIDInRegister.getText();
    if(isValidEmailAddress(email)&&password.length()>=6&&name.length()>=2&&type.length()>=2&&age>0&&phoneNumber.length()>1&&address.length()>1){
        Patient patient=new Patient(name,password,email,age, type.equals("Male"),phoneNumber,address);
        Database db=new Database();
        patient.setId(db.getAllPatients("SELECT * FROM patient ORDER BY ID DESC LIMIT 0, 1").get(0).getId()+1);
        db.insertPatient(patient);
        Parent root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        Stage window = (Stage) registeredBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }else{
        checkCredentials.setVisible(true);
    }

    }


    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public void loginDoctorClicked(ActionEvent actionEvent) throws SQLException, IOException {
        String email= emailID.getText();
        String password= passwordID.getText();
        Database db=new Database();
        ArrayList<Doctor>doctors =db.getAllDoctors("SELECT * FROM doctor WHERE email ='"+email+"' AND Password ='"+password+"'");
        if(doctors.size()==0){
            IncorrectLabelID.setVisible(true);
        }else{
            Doctor doctor=doctors.get(0);
            Doctor.CURR_DOCTOR_ID=doctor.getId();
            Doctor.CURR_DOCTOR_NAME=doctor.getName();
            Doctor.CURR_DOCTOR_SALARY=doctor.getSalary();
            Parent root = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
            Stage window = (Stage) loginBtnDoctor.getScene().getWindow();
            window.setScene(new Scene(root,600,400));
        }


    }

    public void loginPatientClicked(ActionEvent actionEvent) throws SQLException, IOException {
        String email= emailID.getText();
        String password= passwordID.getText();
        Database db=new Database();
        ArrayList<Patient>patients=db.getAllPatients("SELECT * FROM patient WHERE email ='"+email+"' AND Password ='"+password+"'");
        if(patients.size()==0){
            IncorrectLabelID.setVisible(true);
        }else{
            Patient patient=patients.get(0);
            Patient.CURR_PATIENT_ID=patient.getId();
            Patient.CURR_PATIENT_NAME=patient.getName();
            Parent root = FXMLLoader.load(getClass().getResource("patientpage.fxml"));
            Stage window = (Stage) loginBtnPatient.getScene().getWindow();
            window.setScene(new Scene(root,600,400));
        }

    }
}