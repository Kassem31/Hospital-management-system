package com.example.hospital_reservation_sysytem;

import com.example.hospital_reservation_sysytem.Model.Database;
import com.example.hospital_reservation_sysytem.Model.Doctor;
import com.example.hospital_reservation_sysytem.Model.DoctorAppointments;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

public class ViewAppointmentsController implements Initializable {

    public TableView<DoctorAppointments> tableviewID;
    public Button adminBackBtn;
    public TableColumn<DoctorAppointments, Date> dateID;
    public TableColumn <DoctorAppointments, Time>timeID;
    public TableColumn <DoctorAppointments, String>patientNameID;
    public TableColumn <DoctorAppointments, String>genderID;
    public TableColumn<DoctorAppointments, String> phoneNumberID;
    public TableColumn <DoctorAppointments, String>addressID;

    public void adminBackClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminpage.fxml"));

        Stage window = (Stage) adminBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateID.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeID.setCellValueFactory(new PropertyValueFactory<>("time"));
        patientNameID.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        genderID.setCellValueFactory(cellData -> {
            boolean gender = cellData.getValue().isGender();
            String genderAsString;
            if(gender == true)
            {
                genderAsString = "Male";
            }
            else
            {
                genderAsString = "Female";
            }

            return new ReadOnlyStringWrapper(genderAsString);
        });
        phoneNumberID.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressID.setCellValueFactory(new PropertyValueFactory<>("address"));
        if(tableviewID!=null){
            try {
                tableviewID.setItems(getAppointments());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        tableviewID.getSortOrder().add(dateID);
        tableviewID.sort();

    }

    public ObservableList<DoctorAppointments> getAppointments() throws SQLException {
        Database db=new Database();
        ObservableList<DoctorAppointments>observableList= FXCollections.observableArrayList(db.getAllDoctorAppointments(Doctor.CURR_DOCTOR_ID));
        return  observableList;

    }

}
