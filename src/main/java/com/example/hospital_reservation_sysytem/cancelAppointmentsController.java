package com.example.hospital_reservation_sysytem;

        import com.example.hospital_reservation_sysytem.Model.*;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
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
        import java.sql.Time;
        import java.util.ResourceBundle;

public class cancelAppointmentsController implements Initializable {

    public TableView<PatientAppointments> patientTableviewID;
    public Button cancelSelectedAppointmentID;

    @FXML
    private TableColumn<PatientAppointments, Date> appointmentDateID;

    @FXML
    private TableColumn<PatientAppointments, String> appointmentDepartmentID;

    @FXML
    private TableColumn<PatientAppointments, String> appointmentDoctorID;

    @FXML
    private TableColumn<PatientAppointments, Time> appointmentTimeID;

    @FXML
    private Button patientBackBtn;

    @FXML
    public void patientBackClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("patientpage.fxml"));

        Stage window = (Stage) patientBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        appointmentDateID.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointmentTimeID.setCellValueFactory(new PropertyValueFactory<>("time"));
        appointmentDepartmentID.setCellValueFactory(new PropertyValueFactory<>("doctorDepartment"));
        appointmentDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        if(patientTableviewID!=null){
            try {
                patientTableviewID.setItems(getAppointments());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        patientTableviewID.getSortOrder().add(appointmentDateID);
        patientTableviewID.sort();



    }

    public ObservableList<PatientAppointments> getAppointments() throws SQLException {
        Database db=new Database();
        ObservableList<PatientAppointments>observableList= FXCollections.observableArrayList(db.getAllPatientAppointments(Patient.CURR_PATIENT_ID));
        return observableList;
    }


    public void cancelSelectedAppointmentClicked(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println(patientTableviewID.getSelectionModel().getSelectedItem());

        String selectedDoctorName = patientTableviewID.getSelectionModel().getSelectedItem().getDoctorName();
        String selectedDoctorDepartment = patientTableviewID.getSelectionModel().getSelectedItem().getDoctorDepartment();
        Date selectedDate = patientTableviewID.getSelectionModel().getSelectedItem().getDate();
        Time selectedTime = patientTableviewID.getSelectionModel().getSelectedItem().getTime();


        Database db = new Database();
        db.deleteAppointment("DELETE FROM appointment WHERE EXISTS (SELECT Name, department FROM doctor WHERE " +
                "Name = '"+selectedDoctorName+"' AND " +
                "department = '"+selectedDoctorDepartment+"' AND " +
                "Date = '"+selectedDate+"' AND " +
                "Time = '"+selectedTime+"'" +
                ");");

        Parent root = FXMLLoader.load(getClass().getResource("cancelAppointment.fxml"));

        Stage window = (Stage) patientBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }
}
