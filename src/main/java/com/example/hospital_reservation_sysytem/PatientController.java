package com.example.hospital_reservation_sysytem;

import com.example.hospital_reservation_sysytem.Model.Database;
import com.example.hospital_reservation_sysytem.Model.Doctor;
import com.example.hospital_reservation_sysytem.Model.Patient;
import com.example.hospital_reservation_sysytem.Model.PatientAppointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    public Button reserveAppointmentBtn;
    public Button cancelAppointmentBtn;
    public Button patientBackBtn;
    public Label patientNameID;
    public Button logoutBtn;


    public void reserveAppointmentClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reserveAppointment.fxml"));

        Stage window = (Stage) reserveAppointmentBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    public void cancelAppointmentClicked(ActionEvent actionEvent) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("cancelAppointment.fxml"));
        ArrayList<PatientAppointments> pa = new ArrayList<>();
        Stage window = (Stage) cancelAppointmentBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    public void patientBackClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("patientpage.fxml"));

        Stage window = (Stage) patientBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }
    public void LogoutClicked(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(patientNameID!=null){
            patientNameID.setText(Patient.CURR_PATIENT_NAME);
        }
    }
}