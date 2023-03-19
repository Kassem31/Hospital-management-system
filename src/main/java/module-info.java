module com.example.hospital_reservation_sysytem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hospital_reservation_sysytem to javafx.fxml;
    opens com.example.hospital_reservation_sysytem.Model to javafx.fxml;
    exports com.example.hospital_reservation_sysytem;
    exports com.example.hospital_reservation_sysytem.Model;
}