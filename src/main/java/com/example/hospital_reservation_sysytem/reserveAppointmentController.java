package com.example.hospital_reservation_sysytem;

        import com.example.hospital_reservation_sysytem.Model.Appointment;
        import com.example.hospital_reservation_sysytem.Model.Database;
        import com.example.hospital_reservation_sysytem.Model.Doctor;
        import com.example.hospital_reservation_sysytem.Model.Patient;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;
        import javafx.stage.Stage;
        import javafx.collections.FXCollections;
        import java.io.IOException;
        import java.sql.Date;
        import java.sql.SQLException;
        import java.sql.Time;
        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.util.ArrayList;
        import java.lang.String;

public class reserveAppointmentController {

    public Label errorLabel;
    @FXML
    private DatePicker chooseDateID;

    @FXML
    private ComboBox<String> chooseDepartmentID;

    @FXML
    private ComboBox<String> chooseDoctorID;

    @FXML
    private ComboBox<Time> chooseTimeID;

    @FXML
    private Button patientBackBtn;

    @FXML
    private Label priceLabelID;

    @FXML
    private Button reserveAppointmentID;
    @FXML
    void chooseDateSelected(ActionEvent event) {
        Patient.CURR_PATIENT_SELECTED_DATE = Date.valueOf(chooseDateID.getValue());
    }
    @FXML
    void chooseDepartmentSelected(ActionEvent event) throws SQLException {
        Database db=new Database();
        ArrayList<Doctor> departmentDoctors=db.getDoctorsByDepartment((String)chooseDepartmentID.getValue());
        Doctor[] doctorsString = new Doctor[departmentDoctors.size()];
        departmentDoctors.toArray(doctorsString);
        String[] names = new String[doctorsString.length];
        for(int i=0;i<doctorsString.length;i++)
        {
            names[i] = doctorsString[i].getName();
        }
        chooseDoctorID.setItems( FXCollections.observableArrayList(names));
    }

    @FXML
    void chooseDoctorSelected(ActionEvent event) throws  SQLException{
      String selectedDoctor = chooseDoctorID.getValue();
      Database db=new Database();
      ArrayList<Doctor>getselectedDoctor = db.getAllDoctors("SELECT * FROM doctor WHERE Name ='"+selectedDoctor+"' AND department ='"+(String)chooseDepartmentID.getValue()+"'");
      Doctor.CURR_DOCTOR_ID = getselectedDoctor.get(0).getId();
      Doctor.CURR_DOCTOR_SALARY = getselectedDoctor.get(0).getSalary();
      priceLabelID.setText("$ " + String.valueOf(Doctor.CURR_DOCTOR_SALARY));
    }

    @FXML
    void chooseTimeSelected(ActionEvent event) {
      Patient.CURR_PATIENT_SELECTED_TIME = chooseTimeID.getValue();
    }

    @FXML
    public void patientBackClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("patientpage.fxml"));

        Stage window = (Stage) patientBackBtn.getScene().getWindow();
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    void reserveAppointmentClicked(ActionEvent event) throws SQLException{
        Database db=new Database();
        if(chooseTimeID.getValue()==null || chooseDoctorID.getValue()==null || chooseDepartmentID.getValue()==null || chooseDateID.getValue()==null)
        {
            //PUT "MISSING FIELDS" HERE
            errorLabel.setText("    Please fill all fields !");
        }
        else {
            ArrayList<Appointment> Appointments = db.getAllAppointments("SELECT * FROM appointment WHERE DoctorId ='" + Doctor.CURR_DOCTOR_ID + "'AND Time ='" + Patient.CURR_PATIENT_SELECTED_TIME + "'AND Date ='" + Patient.CURR_PATIENT_SELECTED_DATE + "'");
            if (Appointments.size() > 0) {
                // PUT "ALREADY RESERVED" LABEL HERE
                errorLabel.setText("This time is already reserved !");
            } else {
                Appointment reservedAppointment = new Appointment(Patient.CURR_PATIENT_ID, Doctor.CURR_DOCTOR_ID, Patient.CURR_PATIENT_SELECTED_DATE, Patient.CURR_PATIENT_SELECTED_TIME);
                db.insertAppointment(reservedAppointment);
                //PUT "APPOINTMENT SUCCESSFULLY RESERVED" HERE
                errorLabel.setText("Appointment successfully reserved !");
            }
        }
    }
}
