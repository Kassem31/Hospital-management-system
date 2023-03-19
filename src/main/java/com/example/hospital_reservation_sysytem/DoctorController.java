package com.example.hospital_reservation_sysytem;


import com.example.hospital_reservation_sysytem.Model.Doctor;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    public Button viewAppointmentsBtn;
    public Button viewEarningsBtn;
    public Label DoctorNameLabelID;
    public Button logoutBtn;


    public void viewAppointmentsClick(ActionEvent actionEvent) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("viewAppointments.fxml"));

        Stage window = (Stage) viewAppointmentsBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    public void viewEarningsClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("viewEarnings.fxml"));

        Stage window = (Stage) viewEarningsBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(DoctorNameLabelID!=null){
            DoctorNameLabelID.setText(Doctor.CURR_DOCTOR_NAME);
        }

    }

    public void LogoutClicked(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("login-page.fxml"));
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }
}