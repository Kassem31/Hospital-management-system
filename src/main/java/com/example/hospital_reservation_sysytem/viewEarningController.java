package com.example.hospital_reservation_sysytem;

import com.example.hospital_reservation_sysytem.Model.Database;
import com.example.hospital_reservation_sysytem.Model.Doctor;
import com.example.hospital_reservation_sysytem.Model.DoctorEarnings;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class viewEarningController implements Initializable {
    public Button adminBackBtn;
    public TableView<DoctorEarnings> viewEarningTableview;
    public TableColumn<DoctorEarnings, Date> DateTableCol;
    public TableColumn<DoctorEarnings, Float> EarningTableCol;

    public void adminBackClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
        Stage window = (Stage) adminBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateTableCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        EarningTableCol.setCellValueFactory(new PropertyValueFactory<>("earnings"));
        if(viewEarningTableview!=null){
            try {
                viewEarningTableview.setItems(getEarnings());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        viewEarningTableview.getSortOrder().add(DateTableCol);
        viewEarningTableview.sort();
    }
    public ObservableList<DoctorEarnings> getEarnings() throws SQLException {
        Database db=new Database();
        ObservableList<DoctorEarnings>observableList= FXCollections.observableArrayList(db.getAllDoctorEarnings(Doctor.CURR_DOCTOR_ID));
        return  observableList;

    }
}
